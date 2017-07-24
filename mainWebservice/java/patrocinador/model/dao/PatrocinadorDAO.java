


package patrocinador.model.dao;

import patrocinador.model.database.DatabaseFactory;
import patrocinador.model.domain.Patrocinador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatrocinadorDAO {

    private final Connection connection = DatabaseFactory.getDatabase("postgresql").conectar();

    public ArrayList<Patrocinador> listar() {
        String sql = "SELECT * FROM patrocinador";
        ArrayList<Patrocinador> retorno = new ArrayList<Patrocinador>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Patrocinador patrocinador = new Patrocinador();
                patrocinador.setIdPatrocinador(resultado.getInt("id_patrocinador"));
                patrocinador.setNomePatrocinador(resultado.getString("nome_patrocinador"));

                retorno.add(patrocinador);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PatrocinadorDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return retorno;
    } 
}