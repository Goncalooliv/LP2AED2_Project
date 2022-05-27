import com.sun.security.jgss.GSSUtil;
import edu.princeton.cs.algs4.*;
import javafx.scene.chart.XYChart;

import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        InputOutputTxt.readNodesFromFile();
        InputOutputTxt.readUserFromFile();
        InputOutputTxt.readWaysFromFile();

        System.out.println("=========PRINT=POI=ST=========");
        //DataBase.printPoiST();

        DataBase.printUserST();
        DataBase.editUserST(DataBase.userST.get(8),"Anibal",Type.BASIC);
        DataBase.searchUserST(3);
        DataBase.searchPoiST(2);

        //=====AddVisitedPOIbyUser=====//
        DataBase.userST.get(1).addVisitedPOI(2, new Date(2022, 5, 7, 23, 0, 0));
        DataBase.addLog(new Log(new Date(2022, 5, 7, 23, 0, 0), "Passei no Poi2", DataBase.userST.get(1).getName(), DataBase.poiST.get(2).getIdPoi()), DataBase.poiST.get(2));

        DataBase.userST.get(1).addVisitedPOI(1, new Date(2022, 5, 14, 23, 0, 0));
        DataBase.addLog(new Log(new Date(2022, 5, 14, 23, 0, 0), "askzjfhasfhaujfha", DataBase.userST.get(1).getName(), DataBase.poiST.get(1).getIdPoi()), DataBase.poiST.get(1));

        DataBase.userST.get(1).addVisitedPOI(4, new Date(2022, 4, 30, 18, 0, 0));
        DataBase.addLog(new Log(new Date(2022, 4, 30, 18, 0, 0), "Visitei o Poi4", DataBase.userST.get(1).getName(), DataBase.poiST.get(4).getIdPoi()), DataBase.poiST.get(4));

        DataBase.userST.get(2).addVisitedPOI(2, new Date(1980, 1, 28, 12, 0, 0));
        DataBase.addLog(new Log(new Date(1980, 1, 28, 12, 0, 0), "Comprar gelados ao mc", DataBase.userST.get(2).getName(), DataBase.poiST.get(2).getIdPoi()), DataBase.poiST.get(2));

        DataBase.userST.get(2).addVisitedPOI(4, new Date(2022, 5, 19, 10, 0, 0));
        DataBase.addLog(new Log(new Date(2022, 5, 19), "Visitei o Poi4", DataBase.userST.get(2).getName(), DataBase.poiST.get(4).getIdPoi()), DataBase.poiST.get(4));

        DataBase.userST.get(3).addVisitedPOI(4, new Date(2022, 4, 30, 13, 35, 0));
        DataBase.addLog(new Log(new Date(2022, 4, 30, 13, 35, 0), "Passei no Poi4", DataBase.userST.get(3).getName(), DataBase.poiST.get(4).getIdPoi()), DataBase.poiST.get(4));

        DataBase.userST.get(3).addVisitedPOI(2, new Date(2022, 5, 10, 10, 45, 0));
        DataBase.addLog(new Log(new Date(2022, 5, 10, 10, 45, 0), "Comprar Panquecas", DataBase.userST.get(3).getName(), DataBase.poiST.get(2).getIdPoi()), DataBase.poiST.get(2));

        System.out.println("=======================LOGS=========================");
        //Como exemplo escolhi logs do POI com id 4
        DataBase.deleteUserST(DataBase.userST.get(1));
        RedBlackBSTProj<Date, Log> logs = DataBase.poiST.get(4).getPoiLog();
        System.out.println("Logs do POI 4 : ");
        for (Date date : logs.keys()) {
            System.out.println(logs.get(date));
        }

        System.out.println("\n");

        //=====POIsVisitadosPorUmUserNumPeriodoDeTempo=====//
        //=====Requisito5a=====//

        Date dataInicial = new Date(2022, 4, 25);
        Date dataFinal = new Date(2022, 5, 20);
        String subrede = "global";

        ST<Integer, Poi> userVisited;
        System.out.println("\nRequisito 5.a)");
        System.out.println("POI's visitados por " + DataBase.userST.get(1).getName() + ": ");
        DataBase.printIntervaloTempo(dataInicial, dataFinal, subrede);
        userVisited = DataBase.userST.get(1).showVisitedPoi(subrede, dataInicial, dataFinal);
        for (int poiID : userVisited.keys()) {
            System.out.println("ID:" + DataBase.poiST.get(poiID).getIdPoi() + " || Nome: " + DataBase.poiST.get(poiID).getPoiName() + " || Subrede: " + DataBase.poiST.get(poiID).getLocation().getSubrede());
        }

        //=====POIsNaoVisitadosPorUmUserNumPeriodoDeTempo=====//
        //=====Requisito5b=====//

        ST<Integer, Poi> notUserVisited;
        System.out.println("\nRequisito 5.b)");
        System.out.println("POI's não visitados por " + DataBase.userST.get(1).getName() + ": ");
        DataBase.printIntervaloTempo(dataInicial, dataFinal, subrede);
        notUserVisited = DataBase.userST.get(1).showNotVisitedPoi(subrede, dataInicial, dataFinal);
        for (int poiID : notUserVisited) {
            System.out.println("ID:" + DataBase.poiST.get(poiID).getIdPoi() + " || Nome: " + DataBase.poiST.get(poiID).getPoiName() + " || Subrede: " + DataBase.poiST.get(poiID).getLocation().getSubrede());
        }

        DataBase.deleteUserST(DataBase.userST.get(1));

        //=====UsersQueVisitaramUmPoiNumPeriodoDeTempo=====//
        //=====Requisito5c=====//

        ST<Integer, User> usersThatVisited;
        //Como exemplo escolhi o POI2
        usersThatVisited = DataBase.poiST.get(2).allUsersThatVisitedPeriod(dataInicial, dataFinal);
        System.out.println("\nRequisito 5.c)");
        System.out.println("Users that visited POI with id: " + DataBase.poiST.get(2).getIdPoi());
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

        System.out.println("==========PESQUISA=DE=TAG=NUM=NODE==========");
        DataBase.searchTagInNode("traffic_signals");
        System.out.println("\n");
        System.out.println("==========PESQUISATIPOPOI===========");
        //System.out.println(DataBase.nodeST.get(1).getType());
        DataBase.searchTypeInNode("Fire Hydrant");
        System.out.println("\n");
        System.out.println("==========PESQUISA=DE=TAG=NUMA=WAY==========");
        DataBase.searchTagInWay("Praça Nove de Abril");



        System.out.println("====================SEPARACAO====================");

        Way way1 = new Way(1,0,1,20,2);
        Way way2 = new Way(2,1,2,10,3);
        Way way3 = new Way(3,2,3,15,8);
        Way way4 = new Way(4,3,4,6,5);
        Way way5 = new Way(5,4,5,8,4);
        Way way6 = new Way(6,5,6,4,6);
        //Way way7 = new Way(7,2,3,13,10);
        //Way way8 = new Way(8,2,4,17,23);



        EdgeWeightedDigraphProj grafoManual = new EdgeWeightedDigraphProj(7);
        grafoManual.addEdge(way1);
        grafoManual.addEdge(way2);
        grafoManual.addEdge(way3);
        grafoManual.addEdge(way4);
        grafoManual.addEdge(way5);
        grafoManual.addEdge(way6);
        //grafoManual.addEdge(way7);
        //grafoManual.addEdge(way8);

        grafoManual.isConnected();

        System.out.println("Shortest path no grafoManual");

        DataBase.shortestPath(1,6,grafoManual);
        //int[] ArrayVerticeToAvoid = {3};
        //DataBase.pathAvoidingVertice(1,7,grafoManual,ArrayVerticeToAvoid);

        System.out.println("\n");
        System.out.println("=============GRAFO=LIDO=DO=FICHEIRO============");

        EdgeWeightedDigraphProj grafo = new EdgeWeightedDigraphProj(DataBase.poiST.size()+1);
        for(int i = 1; i <= DataBase.wayST.size(); i++){
           grafo.addEdge(DataBase.wayST.get(i));
        }

        System.out.println(grafo);
        grafo.isConnected();

        DataBase.shortestPath(8,12,grafo);

        InputOutputTxt.writeWaysToFile();

        InputOutputTxt.outputGrafo(grafo);
        InputOutputTxt.outputGrafoBin(grafo);

        EdgeWeightedDigraphProj grafo2 = InputOutputTxt.inputGrafoBin();
        System.out.println("=====GRAFO=DO=BINARIO========");
        System.out.println(grafo2);


    }

}
