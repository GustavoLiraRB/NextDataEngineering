package school.cesar.next.covid;

import dao.PessoaDao;
import dao.RegistrolocalizacaoDao;
import io.javalin.Javalin;
import model.Pessoa;
import school.cesar.next.covid.dummydata.routes.DbDummyDataGenMain;
import school.cesar.next.covid.ui.EntradaDadosSuspeitos;
//import sun.jvm.hotspot.debugger.sparc.SPARCThreadContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {

    private static EntityManager entityManager = getEntityManager();

    private static EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("next");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("main/resources/web");})
            .start(9000);

        app.post("/sim", ctx -> {
            String resultadoRota = ctx.formParam("rota");
            String resultadoQuantidade = ctx.formParam("quantidade");
            int rota = Integer.parseInt(resultadoRota);
            int qtdPessoas = Integer.parseInt(resultadoQuantidade);
            for (int i = 0; i < qtdPessoas; i++) {
                DbDummyDataGenMain tc = new DbDummyDataGenMain();
                tc.rota = rota;
                Thread t2 = new Thread(tc);
                t2.setName("Pessoa " + (i+1));
                t2.start();
            }
            //new EntradaDadosSuspeitos(sc).run();
            ctx.redirect("/process.html");
        });


        app.post("/confirm", ctx -> {
            String resultadoId = ctx.formParam("id");
            String resultadoData = ctx.formParam("data");
            String resultadoDias = ctx.formParam("dias");
            int id = Integer.parseInt(resultadoId);
            Date data = new SimpleDateFormat("yyyy-MM-dd").parse(resultadoData);
            int dias = Integer.parseInt(resultadoDias);
            PessoaDao daoP = new PessoaDao();
            List<Pessoa> lista = new ArrayList<Pessoa>();
            lista = daoP.getList();
            Pessoa pessoa = lista.get(id - 1);
            RegistrolocalizacaoDao daoR = new RegistrolocalizacaoDao();
            boolean resultado = false;
            if (data != null) {
                resultado = daoR.marcarInfectado(pessoa, data, dias);
            }
            ctx.redirect("/process.html");
        });

        app.post("/manual", ctx -> {
            String resultadoSituacao = ctx.formParam("situacao");
            String resultadoData = ctx.formParam("data");
            String resultadoLat = ctx.formParam("lat");
            String resultadoLon = ctx.formParam("lon");
            int situacao = Integer.parseInt(resultadoSituacao);
            Date data = new SimpleDateFormat("yyyy-MM-dd").parse(resultadoData);
            double lat = Double.parseDouble(resultadoLat);
            double log = Double.parseDouble(resultadoLon);
            ctx.redirect("/process.html");
        });

        app.post("/atualizar", ctx -> {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("atualizacaoRegistro");
            query.execute();
            entityManager.getTransaction().commit();
            ctx.redirect("/success.html");
        });

        app.post("/end", ctx -> {
            app.stop();
        });

    }
}