package br.com.gerenciamento.repository;

import br.com.gerenciamento.model.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioRepositoryTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioRepositoryTest usuarioRepositoryTest;

    private Usuario usuario;

    @Before
    public void setUp() {
        usuario = new Usuario();
        usuario.setEmail("teste@exemplo.com");
        usuario.setUser("usuarioTeste");
        usuario.setSenha("senha123");
    }

    @Test
    public void testFindByEmail() {
        when(usuarioRepository.findByEmail("teste@exemplo.com")).thenReturn(usuario);

        Usuario usuarioEncontrado = usuarioRepository.findByEmail("teste@exemplo.com");

        Assert.assertNotNull(usuarioEncontrado);
        Assert.assertEquals("teste@exemplo.com", usuarioEncontrado.getEmail());
        Assert.assertEquals("usuarioTeste", usuarioEncontrado.getUser());
    }

    @Test
    public void testBuscarLoginValido() {
        when(usuarioRepository.buscarLogin("usuarioTeste", "senha123")).thenReturn(usuario);

        Usuario usuarioEncontrado = usuarioRepository.buscarLogin("usuarioTeste", "senha123");

        Assert.assertNotNull(usuarioEncontrado);
        Assert.assertEquals("usuarioTeste", usuarioEncontrado.getUser());
        Assert.assertEquals("senha123", usuarioEncontrado.getSenha());
    }

    @Test
    public void testBuscarLoginInvalido() {
        when(usuarioRepository.buscarLogin("usuarioTeste", "senhaErrada")).thenReturn(null);

        Usuario usuarioEncontrado = usuarioRepository.buscarLogin("usuarioTeste", "senhaErrada");

        Assert.assertNull(usuarioEncontrado);
    }

    @Test
    public void testFindByEmailUsuarioNaoEncontrado() {
        when(usuarioRepository.findByEmail("naoexiste@exemplo.com")).thenReturn(null);

        Usuario usuarioEncontrado = usuarioRepository.findByEmail("naoexiste@exemplo.com");

        Assert.assertNull(usuarioEncontrado);
    }
}
