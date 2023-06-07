package Test;

import static org.junit.Assert.*;
import org.junit.*;

import Domini.*;

public class CtrlUsuariTest {
    
    @Before
    public void setUp() {
        CtrlUsuari.RegistrarUsuario("juan", "1234");
        CtrlUsuari.RegistrarUsuario("maria", "abcd");
    }
    
    @Test
    public void testRegistrarUsuario() {
        CtrlUsuari.RegistrarUsuario("pepe", "qwerty");
        assertNotNull(CtrlUsuari.getUsuari("pepe"));
    }
    
    @Test
    public void testRegistrarUsuarioYaExistente() {
        CtrlUsuari.RegistrarUsuario("juan", "5678");
        assertFalse(CtrlUsuari.getUsuari("juan").getPassword().equals("5678"));
    }
    
    @Test
    public void testLogIn() {
        assertNotNull(CtrlUsuari.LogIn("juan", "1234"));
        assertEquals("juan", CtrlUsuari.getUserLogged());
    }
    
    @Test
    public void testLogInNoExisteUsuario() {
        assertNull(CtrlUsuari.LogIn("pedro", "abcd"));
    }
    
    @Test
    public void testLogInContraseñaIncorrecta() {
        assertNull(CtrlUsuari.LogIn("maria", "1234"));
    }
    
    
    @Test
    public void testLogOut() {
        CtrlUsuari.LogIn("juan", "1234");
        assertNotNull(CtrlUsuari.getUserLogged());
        CtrlUsuari.logOut();
        assertNull(CtrlUsuari.getUserLogged());
    }
    
   
    @Test
    public void testEliminarUsuari() {
        CtrlUsuari.eliminarUsuari("juan","1234");
        assertNull(CtrlUsuari.getUsuari("juan"));
    }
    
  
    @Test
    public void testEliminarUsuariNoExiste() {
        CtrlUsuari.eliminarUsuari("pedro","xxxxx");
        assertNotNull(CtrlUsuari.getUsuari("juan"));
        assertNotNull(CtrlUsuari.getUsuari("maria"));
    }
}
