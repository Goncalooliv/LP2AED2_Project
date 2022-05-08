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
        Poi poi4 = new Poi(4, "Universidade Fernando Pessoa", new Location("porto", 41.17284072385078, -8.6112181851571), poiType4, "Valente MERDA oubla");
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
        DataBase.editUserST(user2, "RONALDO", Type.BASIC);

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
        user1.addVisitedPOI(2, new Date(2022, 5, 15, 20, 30, 0));
        user1.addVisitedPOI(4, new Date(2022, 4, 30, 18, 0, 0));
        user1.addVisitedPOI(4, new Date(2022, 5, 7, 19, 45, 0));


        //=====POIsVisitadosPorUmUserNumPeriodoDeTempo=====//
        ST<Date, ArrayList<Poi>> userVisited = new ST<>();
        Date dataInicial = new Date(2022, 4, 25);
        Date dataFinal = new Date(2022, 5, 20);
        System.out.println("POI's visitados por " + user1.getName() + ": ");
        user1.printIntervaloTempo(dataInicial, dataFinal);
        userVisited = user1.showVisitedPoi("global", dataInicial, dataFinal);
        for (Date date : userVisited.keys()) {
            System.out.println("ID:" + user1.visitedPoi.get(date).getIdPoi() + " || Nome: " + user1.visitedPoi.get(date).getPoiName() + " || Data: " + date);
        }

        //=====POIsNaoVisitadosPorUmUserNumPeriodoDeTempo=====//
        ST<Integer, ArrayList<Poi>> notUserVisited = new ST<>();
        System.out.println("\n\nPOI's não visitados por " + user1.getName() + ": ");
        user1.printIntervaloTempo(new Date(2022,4,5),new Date(2022,4,10));
        notUserVisited = user1.showNonVisitedPoi("penafiel",new Date(2022,4,5),new Date(2022,4,10));
        for(int id : notUserVisited){
            System.out.println("ID:" + DataBase.poiST.get(id).getIdPoi() + " || Nome: " + DataBase.poiST.get(id).getPoiName());
        }


    }
}
