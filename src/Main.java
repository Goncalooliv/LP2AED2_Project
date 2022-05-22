import com.sun.security.jgss.GSSUtil;
import edu.princeton.cs.algs4.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //======USER======//
        User user1 = new User("Goncalo", Type.ADMIN);
        User user2 = new User("Jeremias", Type.BASIC);
        User user3 = new User("Madalena", Type.BASIC);
        User user4 = new User(2, "Vasco", Type.ADMIN);
        User user5 = new User(4, "Manuel", Type.BASIC);

        //=====PoiType=====//
        PoiType poiType1 = new PoiType("Charging Station");
        PoiType poiType2 = new PoiType("Fire Hydrant");
        PoiType poiType3 = new PoiType("Traffic Light");
        PoiType poiType4 = new PoiType("Building");

        //=====POI=====//
        Poi poi1 = new Poi(1, "Charging Station do Tribunal", new Location("penafiel", 41.20188103017641, -8.288325584019526), poiType1, "Charging Station localizada no parque de estacionamento do tribunal de Penafiel compatível com todos os veiculos eletricos!");
        Poi poi2 = new Poi(2, "McDonald's", new Location("penafiel", 41.19409386796417, -8.30180231052478), poiType4, "McDonald's de Penafiel, logo a 2 minutos da 'Rotunda Abraco do Povo'! ");
        Poi poi3 = new Poi(3, "Semáforo da Nacional 15", new Location("penafiel", 41.193791067262794, -8.30409439436035), poiType3, "Semáforo para veiculos e pedestres com sinalizador visual(temporizador) e sonoro!");
        Poi poi4 = new Poi(4, "Universidade Fernando Pessoa", new Location("porto", 41.17284072385078, -8.6112181851571), poiType4, "Estudar que é preciso");
        Poi poi5 = new Poi(5, "Boca de Incêndio do Intermarche", new Location("penafiel", 41.125405282878845, -8.29408638982938), poiType2, "Boca de incendio seca, perto do parque de estacionamento");


        //=====InsertUserST=====//
        DataBase.insertUserST(user1);
        DataBase.insertUserST(user2);
        DataBase.insertUserST(user3);
        DataBase.insertUserST(user4);
        DataBase.insertUserST(user5);

        //=====InsertPoiST=====//
        DataBase.insertPoiST(poi1);
        DataBase.insertPoiST(poi2);
        DataBase.insertPoiST(poi3);
        DataBase.insertPoiST(poi4);
        DataBase.insertPoiST(poi5);

        //=====PrintUserST=====//
        DataBase.printUserST();

        //=====PrintPoiST=====//
        DataBase.printPoiST();

        //=====DeleteUserST=====//
        DataBase.deleteUserST(user5);

        //=====DeletePoiST=====//
        DataBase.deletePoiST(poi5);

        //=====PrintUserST=====//
        DataBase.printUserST();         //após a remoção de um User para ver as mudanças

        //=====PrintPoiST=====//
        DataBase.printPoiST();          //após a remoção de um Poi para ver as mudanças

        //=====SearchUserST=====//
        DataBase.searchUserST(3);

        //=====SearchPoiST=====//
        DataBase.searchPoiST(2);

        //=====EditUserST=====//
        DataBase.editUserST(user2, "Ronaldo", Type.BASIC);

        //=====EditPoiST=====//
        DataBase.editPoiST(poi4, "Estadio do Dragao", new Location("porto", 41.161802288279226, -8.583601730616461), poiType4, "Estádio do maior e melhor do mundo");

        System.out.println("\nUser ST depois do edit!");

        //=====PrintUserST=====//
        DataBase.printUserST();         //após editar atributos de um user para ver as mudanças

        System.out.println("\nPoi ST depois do edit!");

        //=====PrintPoiST=====//
        DataBase.printPoiST();


        //=====AddVisitedPOIbyUser=====//
        user1.addVisitedPOI(2, new Date(2022, 5, 7, 23, 0, 0));
        DataBase.addLog(new Log(new Date(2022, 5, 7, 23, 0, 0), "Fui ao Mc comer 17 hamburgueres", user1.getName(), poi2.getIdPoi()), poi2);

        user1.addVisitedPOI(1, new Date(2022, 5, 14, 23, 0, 0));
        DataBase.addLog(new Log(new Date(2022, 5, 14, 23, 0, 0), "askzjfhasfhaujfha", user1.getName(), poi1.getIdPoi()), poi1);

        user1.addVisitedPOI(4, new Date(2022, 4, 30, 18, 0, 0));
        DataBase.addLog(new Log(new Date(2022, 4, 30, 18, 0, 0), "Fui ao dragao ver o melhor in the world", user1.getName(), poi4.getIdPoi()), poi4);

        user2.addVisitedPOI(2, new Date(1980, 1, 28, 12, 0, 0));
        DataBase.addLog(new Log(new Date(1980, 1, 28, 12, 0, 0), "Comprar gelados ao mc", user1.getName(), poi2.getIdPoi()), poi2);

        user2.addVisitedPOI(4, new Date(2022, 5, 19, 10, 0, 0));
        DataBase.addLog(new Log(new Date(2022, 5, 19), "Final da Taça Portugal", user2.getName(), poi4.getIdPoi()), poi4);

        user3.addVisitedPOI(4, new Date(2022, 4, 30, 13, 35, 0));
        DataBase.addLog(new Log(new Date(2022, 4, 30, 13, 35, 0), "Carregar o veiculo por 45 minutos", user3.getName(), poi4.getIdPoi()), poi4);

        user3.addVisitedPOI(2, new Date(2022, 5, 10, 10, 45, 0));
        DataBase.addLog(new Log(new Date(2022, 5, 10, 10, 45, 0), "Comprar Panquecas", user1.getName(), poi2.getIdPoi()), poi2);


        System.out.println("=======================LOGS=========================");
        RedBlackBST<Date, Log> logs = poi4.getPoiLog();
        System.out.println("Logs do POI 4 : ");
        for (Date date : logs.keys()) {
            System.out.println(logs.get(date));
        }

        System.out.println("\n");


        //=====POIsVisitadosPorUmUserNumPeriodoDeTempo=====//
        //=====Requisito5a=====//

        Date dataInicial = new Date(2022, 4, 25);
        Date dataFinal = new Date(2022, 5, 20);
        String subrede = "penafiel";


        ST<Integer, Poi> userVisited;
        System.out.println("\nRequisito 5.a)");
        System.out.println("POI's visitados por " + user1.getName() + ": ");
        DataBase.printIntervaloTempo(dataInicial, dataFinal, subrede);
        userVisited = user1.showVisitedPoi(subrede, dataInicial, dataFinal);
        for (int poiID : userVisited.keys()) {
            System.out.println("ID:" + DataBase.poiST.get(poiID).getIdPoi() + " || Nome: " + DataBase.poiST.get(poiID).getPoiName() + " || Subrede: " + DataBase.poiST.get(poiID).getLocation().getSubrede());
        }


        //=====POIsNaoVisitadosPorUmUserNumPeriodoDeTempo=====//
        //=====Requisito5b=====//

        ST<Integer, Poi> notUserVisited;
        System.out.println("\nRequisito 5.b)");
        System.out.println("POI's não visitados por " + user1.getName() + ": ");
        DataBase.printIntervaloTempo(dataInicial, dataFinal, subrede);
        notUserVisited = user1.showNotVisitedPoi(subrede, dataInicial, dataFinal);
        for (int poiID : notUserVisited) {
            System.out.println("ID:" + DataBase.poiST.get(poiID).getIdPoi() + " || Nome: " + DataBase.poiST.get(poiID).getPoiName() + " || Subrede: " + DataBase.poiST.get(poiID).getLocation().getSubrede());
        }

        //=====UsersQueVisitaramUmPoiNumPeriodoDeTempo=====//
        //=====Requisito5c=====//

        ST<Integer, User> usersThatVisited;
        usersThatVisited = poi2.allUsersThatVisitedPeriod(dataInicial, dataFinal);
        System.out.println("\nRequisito 5.c)");
        System.out.println("Users that visited POI with id: " + poi2.getIdPoi());
        for (int userID : usersThatVisited.keys()) {
            System.out.println(usersThatVisited.get(userID).toString());
        }

        //=====POIsQueNaoForamVisitadosNumPeriodoDeTempo=====//
        System.out.println("\nRequisito 5.d)");
        System.out.println("POIs que nao foram visitados: ");
        DataBase.printIntervaloTempo(dataInicial, dataFinal, subrede);
        Poi.poiNotVisited(dataInicial, dataFinal);

        //DataBase.top5PoiUsedPeriod(dataInicial,dataFinal);


        //=====MetodoNow=====//
        System.out.println("\nMétodo Now:\n");
        DataBase.now();

        //=====LerInputDoTxt=====//
        InputOutputTxt.readNodesFromFile();
        System.out.println("\n");
        InputOutputTxt.readWaysFromFile();
        //Node node1 = DataBase.nodeST.get(1);
        //DataBase.removeNodeST(node1);
        //System.out.println("===============================");
        //System.out.println(DataBase.nodeST.get(1).toString());
        /*for(String s : DataBase.nodeST.get(1).tagST.keys()){
            System.out.println("Tags do Node " + DataBase.nodeST.get(1).getId() + " : " + s + " Valor: " + DataBase.nodeST.get(1).tagST.get(s));
        }*/
        //DataBase.printNodeST();
        InputOutputTxt.writeNodesToFile();
        InputOutputTxt.writeWaysToFile();

        System.out.println("==========PESQUISA=DE=TAG=NUM=NODE==========");
        DataBase.searchTagInNode("traffic_signals");


    }

}
