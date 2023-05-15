package com.example.solicitudes.negocio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appframework.dto.EstadoSolicitudDTO;
import com.example.appframework.dto.NominalesPasivoDTO;
import com.example.appframework.dto.RolesPersSolicitudDTO;
import com.example.appframework.dto.SolicitudDTO;
import com.example.solicitudes.modelo.Beneficio;
import com.example.solicitudes.modelo.BeneficioCausales;
import com.example.solicitudes.modelo.BeneficioCausalesPK;
import com.example.solicitudes.modelo.BeneficioPK;
import com.example.solicitudes.modelo.CodCausalBeneficio;
import com.example.solicitudes.modelo.CodigoEstSol;
import com.example.solicitudes.modelo.EstadoSolicitud;
import com.example.solicitudes.modelo.EstadoSolicitudPK;
import com.example.solicitudes.modelo.NominalesPasivo;
import com.example.solicitudes.modelo.NominalesPasivoPK;
import com.example.solicitudes.modelo.RolesPersSolicitud;
import com.example.solicitudes.modelo.RolesPersSolicitudPK;
import com.example.solicitudes.modelo.Solicitud;
import com.example.solicitudes.repositorio.CodCausalBeneficioRepo;
import com.example.solicitudes.repositorio.CodigoEstSolRepo;
import com.example.solicitudes.repositorio.RolesPersSolicitudRepo;
import com.example.solicitudes.repositorio.SolicitudRepo;
import com.example.solicitudes.utils.EstadosSolicitud;

@Service
public class SolicitudBLImpl implements SolicitudBL, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6254336708940743643L;

	@Autowired
	SolicitudRepo repo;

	@Autowired
	CodigoEstSolRepo estadoRepo;

	@Autowired
	private RolesPersSolicitudRepo rolesPersSolicitudRepo;

	@Autowired
	private CodCausalBeneficioRepo codCausalRepo;

	@Override
	public SolicitudDTO addSolicitud(SolicitudDTO solicitudDTO) throws Exception {
		Solicitud entidad = new Solicitud(null, null, solicitudDTO.getNumeroSolicitud(),
				solicitudDTO.getFechaSolicitud(), solicitudDTO.getCodTipoSolicitud(), solicitudDTO.getCodBeneficio(),
				solicitudDTO.getProcedencia());

		entidad.setRoles(new ArrayList<>());

		if (solicitudDTO.getRoles() != null) {
			for (RolesPersSolicitudDTO dto : solicitudDTO.getRoles()) {
				RolesPersSolicitud rolesper = new RolesPersSolicitud();

				RolesPersSolicitudPK pk = new RolesPersSolicitudPK();
				pk.setSolicitud(entidad);
				pk.setCodRolPersona(dto.getCodRolPersona());
				pk.setIdenficador(dto.getPersIdentificador());
				pk.setFechaVigDesde(LocalDateTime.now());
				rolesper.setPk(pk);
				entidad.getRoles().add(rolesper);
			}
		}

		entidad.setEstado(new ArrayList<>());
		if (solicitudDTO.getEstado() != null) {
			for (EstadoSolicitudDTO dto : solicitudDTO.getEstado()) {
				Optional<CodigoEstSol> estadoOptional = estadoRepo.findById(EstadosSolicitud.INICIADA.getCodigo());
				if (estadoOptional.isEmpty()) {
					throw new Exception("NO EXISTE EL ESTADO.");
				}
				entidad.getEstado().add(new EstadoSolicitud(
						new EstadoSolicitudPK(entidad, estadoOptional.get(), LocalDateTime.now()), dto.getObservasion(),
						dto.getCodViaIngreso(), dto.getCodAgencia(), null, dto.getCodCausalBenef(), dto.getCodLugar()));
			}
		}

		// Beneficio

		BeneficioPK pk = new BeneficioPK(entidad, LocalDateTime.now(), LocalDateTime.now());
		Beneficio beneficio = new Beneficio(pk, null, null);

		if (solicitudDTO.getCodCausalBeneficio() != null) {
			Optional<CodCausalBeneficio> causalBeneficio = codCausalRepo.findById(solicitudDTO.getCodCausalBeneficio());
			if (causalBeneficio.isPresent()) {
				BeneficioCausalesPK causalesPK = new BeneficioCausalesPK(beneficio, LocalDateTime.now(),
						LocalDateTime.now());
				causalesPK.setCodCausalBenef(causalBeneficio.get());
				BeneficioCausales beneficioCausales = new BeneficioCausales(null, null, causalesPK, null, null);
				List<BeneficioCausales> listaBeneficioCausales = new ArrayList<BeneficioCausales>();
				listaBeneficioCausales.add(beneficioCausales);
				beneficio.setBeneficioCausal(listaBeneficioCausales);

			}
		}
		// Nominales

		List<NominalesPasivo> listNominalesPasivos = new ArrayList<>();

		if (solicitudDTO.getNominalPasivo() != null && solicitudDTO.getNominalPasivo().size() == 1) {

			for (NominalesPasivoDTO dto : solicitudDTO.getNominalPasivo()) {
				NominalesPasivoPK nominalesPk = new NominalesPasivoPK(beneficio, dto.getFechaPerDesde(),
						dto.getCodRubroLiq(), dto.getCategNominal(), LocalDateTime.now());

				NominalesPasivo nominalesPasivo = new NominalesPasivo(null, null, nominalesPk,
						dto.getPersIdentificador(), dto.getCodValorMon(), dto.getCategNominal(), dto.getFechaBasico(),
						dto.getFechaPerHasta(), null);

				listNominalesPasivos.add(nominalesPasivo);
			}
		} else if (solicitudDTO.getNominalPasivo() != null) {
			List<NominalesPasivoDTO> listaordenada = solicitudDTO.getNominalPasivo();
			Collections.sort(listaordenada, new Comparator<NominalesPasivoDTO>() {

				@Override
				public int compare(NominalesPasivoDTO o1, NominalesPasivoDTO o2) {

					return o1.getFechaPerDesde().compareTo(o2.getFechaPerDesde());
				}
			});

			for (int i = 0; i < listaordenada.size() - 1; i++) {

				NominalesPasivoDTO dto = listaordenada.get(i);
				if (dto.getFechaPerHasta().equals(listaordenada.get(i + 1).getFechaPerDesde())) {
					NominalesPasivoPK nominalesPk = new NominalesPasivoPK(beneficio, dto.getFechaPerDesde(),
							dto.getCodRubroLiq(), dto.getCategNominal(), LocalDateTime.now());

					NominalesPasivo nominalesPasivo = new NominalesPasivo(null, null, nominalesPk,
							dto.getPersIdentificador(), dto.getCodValorMon(), dto.getCategNominal(),
							dto.getFechaBasico(), dto.getFechaPerHasta(), null);

					listNominalesPasivos.add(nominalesPasivo);

				} else {
					throw new Exception("La fecha Per hasta de la nominal es incorrecta");

				}

			}
			int ultimo = listaordenada.size();
			NominalesPasivoDTO ultimodto = listaordenada.get(ultimo - 1);
			NominalesPasivoPK ultimoPk = new NominalesPasivoPK(beneficio, ultimodto.getFechaPerDesde(),
					ultimodto.getCodRubroLiq(), ultimodto.getCategNominal(), LocalDateTime.now());
			NominalesPasivo ultimoNominalesPasivo = new NominalesPasivo(null, null, ultimoPk,
					ultimodto.getPersIdentificador(), ultimodto.getCodValorMon(), ultimodto.getCategNominal(),
					ultimodto.getFechaBasico(), ultimodto.getFechaPerHasta(), null);

			listNominalesPasivos.add(ultimoNominalesPasivo);
		}

		beneficio.setNominalesPasivos(listNominalesPasivos);

		List<Beneficio> listaBeneficios = new ArrayList<Beneficio>();

		listaBeneficios.add(beneficio);
		entidad.setBeneficio(listaBeneficios);

		Solicitud solicitud = repo.save(entidad);
		solicitudDTO.setNumeroSolicitud(solicitud.getNrosolicitud());
		return solicitudDTO;
	}

	@Override
	public List<SolicitudDTO> listarSolicitud() {
//		List<Solicitud> lista = repo.findAll();
//		List<SolicitudDTO> listaDTO = new ArrayList<>();
//		for (Solicitud solicitud : lista) {
//			SolicitudDTO solicitudDTO = new SolicitudDTO();
//			solicitudDTO.setCodBeneficio(solicitud.getCodBeneficio());
//			solicitudDTO.setCodTipoSolicitud(solicitud.getCodTipoSolicitud());
//			solicitudDTO.setFechaSolicitud(solicitud.getFechaSolicitud());
//			solicitudDTO.setNumeroSolicitud(solicitud.getNrosolicitud());
//			solicitudDTO.setProcedencia(solicitud.getProcedencia());
//			listaDTO.add(solicitudDTO);
//		}
//		return listaDTO;

		return repo.findAll().stream().map(solicitud -> {

			SolicitudDTO dto = new SolicitudDTO(solicitud.getNrosolicitud(), solicitud.getFechaSolicitud(),
					solicitud.getCodTipoSolicitud(), solicitud.getCodBeneficio(), solicitud.getProcedencia());

			EstadoSolicitud estadoActual = solicitud.getEstado().stream().filter(e -> e.getFechaVigHasta() == null)
					.findFirst().orElse(null);

			dto.setCodigoEstado(estadoActual != null ? estadoActual.getPk().getCodEstSolBen().getId() : null);
			dto.setDescripcionEstado(
					estadoActual != null ? estadoActual.getPk().getCodEstSolBen().getDescripcion() : null);

			/**
			 * MAPEAR ROLES
			 */

			dto.setRoles(solicitud.getRoles().stream().filter(r -> r.getFechaVigHasta() == null).map(r -> {
				return new RolesPersSolicitudDTO(r.getPk().getIdenficador(), r.getPk().getCodRolPersona());
			}).collect(Collectors.toList()));

			return dto;
		}).collect(Collectors.toList());

	}

	@Override
	public SolicitudDTO actualizarSolicitud(SolicitudDTO solicitudDTO, Long nuSolicitud) throws Exception {
		Optional<Solicitud> solicitudPorID = repo.findById(nuSolicitud);
		if (!solicitudPorID.isPresent()) {
			throw new Exception("NO EXISTE LA SOLICITUD");
		}
		Solicitud solicitud = solicitudPorID.get();
		solicitud.setCodBeneficio(solicitudDTO.getCodBeneficio());
		solicitud.setCodTipoSolicitud(solicitudDTO.getCodTipoSolicitud());
		solicitud.setFechaSolicitud(solicitudDTO.getFechaSolicitud());
		solicitud.setProcedencia(solicitudDTO.getProcedencia());

		/**
		 * actualizar roles
		 */

		LocalDateTime fechaVidDesde = LocalDateTime.now().plusDays(2l);

		List<RolesPersSolicitud> listaRoles = solicitud.getRoles().stream().filter(e -> e.getFechaVigHasta() == null)
				.collect(Collectors.toList());

		for (RolesPersSolicitudDTO dto : solicitudDTO.getRoles()) {
			RolesPersSolicitud rolesper = new RolesPersSolicitud();

			RolesPersSolicitudPK pk = new RolesPersSolicitudPK();
			pk.setSolicitud(solicitud);
			pk.setCodRolPersona(dto.getCodRolPersona());
			pk.setIdenficador(dto.getPersIdentificador());
			pk.setFechaVigDesde(fechaVidDesde);
			rolesper.setPk(pk);
			solicitud.getRoles().add(rolesper);
		}

		for (RolesPersSolicitud rolesPersSolicitud : listaRoles) {
			rolesPersSolicitud.setFechaVigHasta(fechaVidDesde);
		}

		/**
		 * actulizar beneficio
		 * 
		 */

		List<Beneficio> beneficiosActivos = solicitud.getBeneficio().stream().filter(b -> b.getFechaPerHasta() == null)
				.collect(Collectors.toList());

		Beneficio beneficio = new Beneficio(new BeneficioPK(solicitud, fechaVidDesde, fechaVidDesde), null, null);

		List<BeneficioCausales> list = new ArrayList<>();
		for (Beneficio beneficios : beneficiosActivos) {
			list.addAll(beneficios.getBeneficioCausal().stream().filter(b -> b.getFechaPerHasta() == null)
					.collect(Collectors.toList()));
		}

		if (solicitudDTO.getCodCausalBeneficio() != null) {

			Optional<CodCausalBeneficio> causalBeneficioOptional = codCausalRepo
					.findById(solicitudDTO.getCodCausalBeneficio());

			if (causalBeneficioOptional.isEmpty()) {
				throw new Exception("NO EXISTE EL COD CAUSAL.");
			}

			List<BeneficioCausales> listaBeneficioC = new ArrayList<>();
			BeneficioCausalesPK beneficioCausalesPK = new BeneficioCausalesPK(beneficio, fechaVidDesde, fechaVidDesde);
			beneficioCausalesPK.setCodCausalBenef(causalBeneficioOptional.get());
			BeneficioCausales beneficioCausales = new BeneficioCausales(null, null, beneficioCausalesPK, null,
					fechaVidDesde);
			listaBeneficioC.add(beneficioCausales);
			beneficio.setBeneficioCausal(listaBeneficioC);

//			for (BeneficioCausales bCausal : list) {
//				bCausal.setFechaVigHasta(LocalDateTime.now());
//			}

			beneficiosActivos.stream().forEach(b -> b.getBeneficioCausal().stream()
					.filter(c -> c.getFechaVigHasta() == null).forEach(c -> c.setFechaVigHasta(LocalDateTime.now())));

		}

		// Nominales
//		List<NominalesPasivo> lisNominalesPasivosFecha = new ArrayList<>();
//		for (Beneficio beneficios : beneficiosActivos) {
//			lisNominalesPasivosFecha.addAll(beneficios.getNominalesPasivos().stream()
//					.filter(b -> b.getFechaPerHasta() == null).collect(Collectors.toList()));
//		}

		List<NominalesPasivo> listNominalesPasivos = new ArrayList<>();

		if (solicitudDTO.getNominalPasivo().size() == 1) {

			for (NominalesPasivoDTO dto : solicitudDTO.getNominalPasivo()) {
				NominalesPasivoPK nominalesPk = new NominalesPasivoPK(beneficio, dto.getFechaPerDesde(),
						dto.getCodRubroLiq(), dto.getCategNominal(), LocalDateTime.now());

				NominalesPasivo nominalesPasivo = new NominalesPasivo(null, null, nominalesPk,
						dto.getPersIdentificador(), dto.getCodValorMon(), dto.getCategNominal(), dto.getFechaBasico(),
						dto.getFechaPerHasta(), null);

				listNominalesPasivos.add(nominalesPasivo);
				beneficio.setNominalesPasivos(listNominalesPasivos);
			}
//			for (NominalesPasivo nominal : lisNominalesPasivosFecha) {
//				nominal.setFechaVigHasta(LocalDateTime.now());
//			}
			beneficiosActivos.stream().forEach(b -> b.getNominalesPasivos().stream()
					.filter(c -> c.getFechaVigHasta() == null).forEach(c -> c.setFechaVigHasta(LocalDateTime.now())));

		} else {
			List<NominalesPasivoDTO> listaordenada = solicitudDTO.getNominalPasivo();
			Collections.sort(listaordenada, new Comparator<NominalesPasivoDTO>() {

				@Override
				public int compare(NominalesPasivoDTO o1, NominalesPasivoDTO o2) {

					return o1.getFechaPerDesde().compareTo(o2.getFechaPerDesde());
				}
			});

			for (int i = 0; i < listaordenada.size() - 1; i++) {

				NominalesPasivoDTO dto = listaordenada.get(i);
				if (dto.getFechaPerHasta().equals(listaordenada.get(i + 1).getFechaPerDesde())) {
					NominalesPasivoPK nominalesPk = new NominalesPasivoPK(beneficio, dto.getFechaPerDesde(),
							dto.getCodRubroLiq(), dto.getCategNominal(), LocalDateTime.now());

					NominalesPasivo nominalesPasivo = new NominalesPasivo(null, null, nominalesPk,
							dto.getPersIdentificador(), dto.getCodValorMon(), dto.getCategNominal(),
							dto.getFechaBasico(), dto.getFechaPerHasta(), null);

					listNominalesPasivos.add(nominalesPasivo);

				} else {
					throw new Exception("La fecha Per hasta de la nominal es incorrecta");

				}

			}
			int ultimo = listaordenada.size();
			NominalesPasivoDTO ultimodto = listaordenada.get(ultimo - 1);
			NominalesPasivoPK ultimoPk = new NominalesPasivoPK(beneficio, ultimodto.getFechaPerDesde(),
					ultimodto.getCodRubroLiq(), ultimodto.getCategNominal(), LocalDateTime.now());
			NominalesPasivo ultimoNominalesPasivo = new NominalesPasivo(null, null, ultimoPk,
					ultimodto.getPersIdentificador(), ultimodto.getCodValorMon(), ultimodto.getCategNominal(),
					ultimodto.getFechaBasico(), ultimodto.getFechaPerHasta(), null);

			listNominalesPasivos.add(ultimoNominalesPasivo);
			beneficio.setNominalesPasivos(listNominalesPasivos);
//			for (NominalesPasivo nominal : lisNominalesPasivosFecha) {
//				nominal.setFechaVigHasta(LocalDateTime.now());
//			}
			beneficiosActivos.stream().forEach(b -> b.getNominalesPasivos().stream()
					.filter(c -> c.getFechaVigHasta() == null).forEach(c -> c.setFechaVigHasta(LocalDateTime.now())));

		}

		solicitud.getBeneficio().add(beneficio);

		beneficiosActivos.stream().forEach(b -> b.setFechaVigHasta(LocalDateTime.now().plusDays(2l)));

//		for (Beneficio beneficio2 : listabeneficios) {
//			beneficio2.setFechaVigHasta(LocalDateTime.now());
//		}

		solicitud = repo.save(solicitud);

		return solicitudDTO;
	}

	@Override
	public void eliminarSolicitud(Long nuSolicitud) throws Exception {

		Optional<Solicitud> solicitudPorID = repo.findById(nuSolicitud);
		if (!solicitudPorID.isPresent()) {
			throw new Exception("NO EXISTE LA SOLICITUD");
		}
		repo.deleteById(nuSolicitud);
	}

	@Override
	@Transactional
	public SolicitudDTO actualizarEstadoSolicitud(Long numeroSolicitud, Long estado,
			EstadoSolicitudDTO estadoSolicitudDTO) throws Exception {

		Optional<Solicitud> solicitudOptional = repo.findById(numeroSolicitud);

		if (!solicitudOptional.isPresent()) {
			throw new Exception("NO EXISTE LA SOLICITUD");
		}

		Solicitud solicitud = solicitudOptional.get();

		EstadoSolicitud estadoActual = solicitud.getEstado().stream().filter(e -> e.getFechaVigHasta() == null)
				.findFirst().orElse(null);

		if (estadoActual == null) {
			throw new Exception("DATOS INCORRECTOS EN BASE.");
		}

		if (estadoActual.getPk().getCodEstSolBen().getPermiteCambio().equals("N")) {
			throw new Exception("NO SE PUEDE ACTUALIZAR ESTADO,  NO PERMITE CAMBIO. ");
		}

		Optional<CodigoEstSol> estadoOptional = estadoRepo.findById(estado);
		if (estadoOptional.isEmpty()) {
			throw new Exception("NO EXISTE EL ESTADO.");
		}
		LocalDateTime fechaVigDesde = LocalDateTime.now();
		EstadoSolicitud nuevoEstado = new EstadoSolicitud(
				new EstadoSolicitudPK(solicitud, estadoOptional.get(), fechaVigDesde),
				estadoSolicitudDTO.getObservasion(), estadoSolicitudDTO.getCodViaIngreso(),
				estadoSolicitudDTO.getCodAgencia(), null, estadoSolicitudDTO.getCodCausalBenef(),
				estadoSolicitudDTO.getCodLugar());

		estadoActual.setFechaVigHasta(fechaVigDesde);

		solicitud.getEstado().add(nuevoEstado);

		repo.save(solicitud);
		return new SolicitudDTO(solicitud.getNrosolicitud(), null, null, null, null);
	}

	@Override
	public List<SolicitudDTO> obtenerSolicitudPersona(Long persIdentificador, Long codrolpersona) throws Exception {
		List<Solicitud> solicitud = repo.findAll();
		List<SolicitudDTO> solicitudDTO = new ArrayList<>();
		for (Solicitud solicitud2 : solicitud) {
			List<RolesPersSolicitud> roles = solicitud2.getRoles();
			for (RolesPersSolicitud rolesPersSolicitud : roles) {
				if (rolesPersSolicitud.getPk().getIdenficador() == persIdentificador) {

					SolicitudDTO dto = new SolicitudDTO(solicitud2.getNrosolicitud(), solicitud2.getFechaSolicitud(),
							solicitud2.getCodTipoSolicitud(), solicitud2.getCodBeneficio(),
							solicitud2.getProcedencia());
					solicitudDTO.add(dto);

				} else if (rolesPersSolicitud.getPk().getCodRolPersona() == codrolpersona) {
					SolicitudDTO dto = new SolicitudDTO(solicitud2.getNrosolicitud(), solicitud2.getFechaSolicitud(),
							solicitud2.getCodTipoSolicitud(), solicitud2.getCodBeneficio(),
							solicitud2.getProcedencia());
					solicitudDTO.add(dto);
				}

			}
		}
		if (solicitudDTO.size() == 0) {
			throw new Exception("No existe solicitus con esa persona o codigo de persona");
		}

		return solicitudDTO;
	}

	@Override
	@Transactional
	public List<SolicitudDTO> obtenerSolicitudPorPersona(Long persIdentificador, Long codrolpersona) throws Exception {

		List<RolesPersSolicitud> rolesSolicitudes = new ArrayList<>();
		if (codrolpersona != null) {
			rolesSolicitudes = rolesPersSolicitudRepo.findByPkIdenficadorAndPkCodRolPersona(persIdentificador,
					codrolpersona);
		} else {
			rolesSolicitudes = rolesPersSolicitudRepo.findByPkIdenficador(persIdentificador);
		}

		if (rolesSolicitudes.isEmpty()) {
			return new ArrayList<>();
		}

		List<Solicitud> listaSolicitud = rolesSolicitudes.stream().map(r -> r.getPk().getSolicitud())
				.collect(Collectors.toList());

		return listaSolicitud.stream().distinct().map(s -> {
			return new SolicitudDTO(s.getNrosolicitud(), s.getFechaSolicitud(), s.getCodTipoSolicitud(),
					s.getCodBeneficio(), s.getProcedencia());
		}).collect(Collectors.toList());

	}

	@Override
	@Transactional
	public SolicitudDTO obtenerSolicitud(Long numeroSolicitud) throws Exception {
		Optional<Solicitud> solicitud = repo.findById(numeroSolicitud);
		if (!solicitud.isPresent()) {
			throw new Exception("La solicitud no existe");
		}
		SolicitudDTO dto = new SolicitudDTO(solicitud.get().getNrosolicitud(), solicitud.get().getFechaSolicitud(),
				solicitud.get().getCodTipoSolicitud(), solicitud.get().getCodBeneficio(),
				solicitud.get().getProcedencia());

		EstadoSolicitud estadoActual = solicitud.get().getEstado().stream().filter(e -> e.getFechaVigHasta() == null)
				.findFirst().orElse(null);

		dto.setCodigoEstado(estadoActual != null ? estadoActual.getPk().getCodEstSolBen().getId() : null);
		dto.setDescripcionEstado(estadoActual != null ? estadoActual.getPk().getCodEstSolBen().getDescripcion() : null);

		dto.setRoles(solicitud.get().getRoles().stream().filter(r -> r.getFechaVigHasta() == null).map(r -> {
			return new RolesPersSolicitudDTO(r.getPk().getIdenficador(), r.getPk().getCodRolPersona());
		}).collect(Collectors.toList()));

		Optional<Beneficio> beneficioActivoOptional = solicitud.get().getBeneficio().stream()
				.filter(b -> b.getFechaVigHasta() == null).findFirst();

		if (beneficioActivoOptional.isPresent()) {

			Optional<BeneficioCausales> causalesOptional = beneficioActivoOptional.get().getBeneficioCausal().stream()
					.filter(c -> c.getFechaVigHasta() == null).findFirst();

			dto.setCodCausalBeneficio(causalesOptional.isPresent()
					? causalesOptional.get().getPk().getCodCausalBenef().getCodCausalBeneficio()
					: null);

//			if (causalesOptional.isPresent()) {
//				dto.setCodCausalBeneficio(causalesOptional.get().getPk().getCodCausalBenef().getCodCausalBeneficio());
//			} else {
//				dto.setCodCausalBeneficio(null);
//			}

			List<NominalesPasivo> listaNominalesPasivos = beneficioActivoOptional.get().getNominalesPasivos().stream()
					.filter(n -> n.getFechaVigHasta() == null).collect(Collectors.toList());

//			List<NominalesPasivo> listaNominalesPasivosActivos = new ArrayList<NominalesPasivo>();
//
//			for (int i = 0; i < beneficioActivoOptional.get().getNominalesPasivos().size(); i++) {
//				if (beneficioActivoOptional.get().getNominalesPasivos().get(i).getFechaVigHasta() == null) {
//					listaNominalesPasivosActivos.add(beneficioActivoOptional.get().getNominalesPasivos().get(i));
//				}
//			}

			dto.setNominalPasivo(listaNominalesPasivos.stream().map(e -> {
				NominalesPasivoDTO dtoPasivoDTO = new NominalesPasivoDTO();
				dtoPasivoDTO.setPersIdentificador(e.getPersIdentificador());
				dtoPasivoDTO.setNominal(e.getNominal());
				dtoPasivoDTO.setFechaBasico(e.getFechaBasico());
				dtoPasivoDTO.setFechaPerDesde(e.getPk().getFechaPerDesde());
				dtoPasivoDTO.setFechaPerHasta(e.getFechaPerHasta());
				dtoPasivoDTO.setCategNominal(e.getPk().getCategNominal());
				dtoPasivoDTO.setCodRubroLiq(e.getPk().getCodRubroLiq());
				dtoPasivoDTO.setCodValorMon(e.getCodValorMon());

				return dtoPasivoDTO;
			}).collect(Collectors.toList()));

		}

		return dto;
	}

}
