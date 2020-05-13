package school.cesar.next.covid.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AtualizarSituacao {
    private ConexaoLocal conexaoLocal = new ConexaoLocal();
    private Connection connection = conexaoLocal.getconnection();

    public AtualizarSituacao() throws SQLException {
    }

    public Integer lerSituacao(Pessoa pessoa) {
        try{
            String id = pessoa.id;
            PreparedStatement selectPessoa = connection.prepareStatement("SELECT situacao_id from NextDataEngineering.Pessoa WHERE id=(?)");
            selectPessoa.setString(1, id);
            ResultSet resultado = selectPessoa.executeQuery();
            resultado.first();
            Integer situacao = resultado.getInt(1);
            selectPessoa.close();
            resultado.close();
            return situacao;
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return 0;
        }
    }
    public boolean atualizarSituacao(Integer situacao_id, Pessoa pessoa) {
        Integer situacao = lerSituacao(pessoa);
        String id = pessoa.id;
        if (situacao != situacao_id){
            try{

                PreparedStatement updateSituacao = connection.prepareStatement("UPDATE NextDataEngineering.Pessoa SET situacao_id = (?) WHERE id=(?)");
                updateSituacao.setInt(1, situacao_id);
                updateSituacao.setString(2, id);
                updateSituacao.executeUpdate();
                updateSituacao.close();

            } catch(SQLException sqle){
            sqle.printStackTrace();}
        return true;}
            else{ System.out.println("A pessoa "+id+" já se encontra com essa situação.");return false; }

    }
    public void atualizarHistorico(Date data, Pessoa pessoa, Integer situacao_id) {

        try{
                String id = pessoa.id;
                java.sql.Date sqlDate = (java.sql.Date) data;
                PreparedStatement updateHistorico = connection.prepareStatement("insert into NextDataEngineering.HistoricoSituacao (pessoa_id, Data, situacao_id) values (?,?,?)");
                updateHistorico.setString(1, id);
                updateHistorico.setDate(2, sqlDate);
                updateHistorico.setInt(3, situacao_id);
                updateHistorico.executeUpdate();
            updateHistorico.close();

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }




}

