package com.ciandt.hackathon.sustentability.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.ciandt.hackathon.fundation.exceptions.BusinessException;
import com.ciandt.hackathon.sustentability.model.ProfileEntity;
import com.ciandt.hackathon.sustentability.repository.ProfileRepository;

public class UpdateProfileTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void ao_cadastrar_um_perfil_novo_nao_deve_repetir_email() throws Exception {

	    expectedEx.expect(BusinessException.class);
	    expectedEx.expectMessage("E-mail já cadastrado.");

		ProfileServiceImpl service = new ProfileServiceImpl();

		ProfileEntity loggedUser = null;
		ProfileEntity profile = new ProfileEntity(1l, "Hackathon", "hackathon@ciandt.com", "", "");
		ProfileEntity storeUser = new ProfileEntity(1l, "Michel Jackson", "hackathon@ciandt.com", "", "");

		ProfileRepository profileRepositoryMock = Mockito.mock(ProfileRepository.class);
		Mockito.when(profileRepositoryMock.get(Mockito.eq(1l))).thenReturn(storeUser);
		Mockito.when(profileRepositoryMock.getByProperty(Mockito.eq("email"), Mockito.eq("hackathon@ciandt.com"))).thenReturn(storeUser);

		// Inject mock into private field:
		ReflectionTestUtils.setField(service, "profileepository", profileRepositoryMock, ProfileRepository.class);

		service.updateProfile(profile, loggedUser);

	}

	@Test
	public void so_pode_atualizar_o_perfil_com_email_do_usuario_logado() throws Exception {

	    expectedEx.expect(BusinessException.class);
	    expectedEx.expectMessage("Operação não permitida.");

		ProfileServiceImpl service = new ProfileServiceImpl();

		ProfileEntity loggedUser = new ProfileEntity(1l, "Michel Jackson", "mj@ciandt.com", "", "");
		ProfileEntity profile = new ProfileEntity(1l, "Hackathon", "hackathon@ciandt.com", "", "");
		ProfileEntity storeUser = new ProfileEntity(1l, "Michel Jackson", "mj@ciandt.com", "", "");

		ProfileRepository profileRepositoryMock = Mockito.mock(ProfileRepository.class);
		Mockito.when(profileRepositoryMock.get(Mockito.eq(1l))).thenReturn(storeUser);
		Mockito.when(profileRepositoryMock.getByProperty(Mockito.eq("email"), Mockito.eq("mj@ciandt.com"))).thenReturn(storeUser);

		// Inject mock into private field:
		ReflectionTestUtils.setField(service, "profileepository", profileRepositoryMock, ProfileRepository.class);

		service.updateProfile(profile, loggedUser);

	}

	@Test
	public void ao_atualizar_perfil_deve_ser_o_mesmo_do_usuario_logado() throws Exception {

		ProfileServiceImpl service = new ProfileServiceImpl();

		ProfileEntity loggedUser =  new ProfileEntity(1l, "Michel Jackson", "hackathon@ciandt.com", "", "");
		ProfileEntity profile = new ProfileEntity(1l, "Hackathon", "hackathon@ciandt.com", "pass", "add");
		ProfileEntity storeUser = new ProfileEntity(1l, "Michel Jackson", "hackathon@ciandt.com", "", "");

		ProfileRepository profileRepositoryMock = Mockito.mock(ProfileRepository.class);
		Mockito.when(profileRepositoryMock.get(Mockito.eq(1l))).thenReturn(storeUser);
		Mockito.when(profileRepositoryMock.getByProperty(Mockito.eq("email"), Mockito.eq("hackathon@ciandt.com"))).thenReturn(storeUser);

		// Inject mock into private field:
		ReflectionTestUtils.setField(service, "profileepository", profileRepositoryMock, ProfileRepository.class);

		service.updateProfile(profile, loggedUser);

		Mockito.verify(profileRepositoryMock).put( Mockito.argThat(
				new ArgumentMatcher<ProfileEntity> () {
					@Override
					public boolean matches(Object obj) {

						if(obj instanceof ProfileEntity){
							ProfileEntity e = (ProfileEntity) obj;
							return
									"Hackathon".equals(e.getName()) == true &&
									"hackathon@ciandt.com".equals(e.getEmail()) == true &&
									"pass".equals(e.getPassword()) == true  &&
									"add".equals(e.getAddress()) == true ;
						}

						return false;
					}
				}
		));

	}

	@Test
	public void ao_cadastrar_perfil_deve_salvar_email_id_vazio() throws Exception {

		ProfileServiceImpl service = new ProfileServiceImpl();

		ProfileEntity loggedUser =  null;
		ProfileEntity profile = new ProfileEntity(1l, "Hackathon", "hackathon@ciandt.com", "pass", "add");
		ProfileEntity storeUser = null;

		ProfileRepository profileRepositoryMock = Mockito.mock(ProfileRepository.class);
		Mockito.when(profileRepositoryMock.get(Mockito.eq(1l))).thenReturn(storeUser);
		Mockito.when(profileRepositoryMock.getByProperty(Mockito.eq("email"), Mockito.eq("hackathon@ciandt.com"))).thenReturn(storeUser);

		// Inject mock into private field:
		ReflectionTestUtils.setField(service, "profileepository", profileRepositoryMock, ProfileRepository.class);

		service.updateProfile(profile, loggedUser);

		Mockito.verify(profileRepositoryMock).put( Mockito.argThat(
				new ArgumentMatcher<ProfileEntity> () {
					@Override
					public boolean matches(Object obj) {

						if(obj instanceof ProfileEntity){
							ProfileEntity e = (ProfileEntity) obj;
							return
									null == e.getId() &&
									"Hackathon".equals(e.getName()) == true &&
									"hackathon@ciandt.com".equals(e.getEmail()) == true &&
									"pass".equals(e.getPassword()) == true  &&
									"add".equals(e.getAddress()) == true ;
						}

						return false;
					}
				}
		));
	}

}
