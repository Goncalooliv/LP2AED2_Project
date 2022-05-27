import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class InputOutputTxt implements Serializable{

    public static void readUserFromFile(){
        String content;
        In input = new In(".//data//InputUser.txt");
        if (!input.exists()) {
            System.out.println("File doesn't exist or something is wrong!");
        }else{
            while(input.hasNextLine()) {
                content = input.readLine();
                int userNumber = Integer.parseInt(content);
                for(int i = 0; i < userNumber; i++){
                    content = input.readLine();
                    String[] inputUser = content.split(",",3);
                    //System.out.println("TIPO DE USER: " + inputUser[2]);
                    User user = new User(Integer.parseInt(inputUser[0]),inputUser[1], Type.stringToType(inputUser[2]));
                    DataBase.insertUserST(user);
                }
            }
        }
    }


    public static void writeUserToFile(){
        Out out  = new Out(".//data//OutputUsers.txt");
        out.println(DataBase.userST.size());
        for(int userID : DataBase.userST.keys()){
            out.print(userID + "," + DataBase.userST.get(userID).getName() + "," + DataBase.userST.get(userID).getType());
            out.println();
        }
    }

    public static void readNodesFromFile() {
        String content;
        In input = new In(".//data//dataset1_nodes.txt");
        if (!input.exists()) {
            System.out.println("File doesn't exist or something is wrong!");
        } else {
            while (input.hasNextLine()) {
                content = input.readLine();
                int nodeNumber = Integer.parseInt(content);
                for (int i = 0; i < nodeNumber; i++) {
                    content = input.readLine();
                    String[] inputNode = content.split(",", 8);
                    //System.out.println("Id: " + inputNode[0] + " Lat: " + inputNode[2] + " Long: " + inputNode[3]);
                    Poi poi = new Poi(Integer.parseInt(inputNode[0]),inputNode[1],new Location(inputNode[4], Double.parseDouble(inputNode[2]),Double.parseDouble(inputNode[3])),new PoiType(inputNode[5]));
                    Node node = new Node(Integer.parseInt(inputNode[0]),inputNode[1],new Location(inputNode[4], Double.parseDouble(inputNode[2]),Double.parseDouble(inputNode[3])),new PoiType(inputNode[5]));
                    DataBase.insertPoiST(poi);
                    DataBase.insertNodeST(node);
                    if (Integer.parseInt(inputNode[6]) != 0) {
                        String[] tags = inputNode[7].split(",", Integer.parseInt(inputNode[6]));
                        poi.tagArray.addAll(Arrays.asList(tags));
                        for (int j = 0; j < poi.tagArray.size(); j += 2) {
                            poi.tagST.put(poi.tagArray.get(j), poi.tagArray.get(j + 1));
                        }
                    }
                    /*for(String s : node.tagST.keys()){
                        System.out.println("Key : " + s + " || Value: " + node.tagST.get(s));
                    }*/
                }

            }

        }
    }

    public static void readWaysFromFile() {
        String content;
        In input = new In(".//data//dataset1_ways_nodepairs.txt");
        if (!input.exists()) {
            System.out.println("File doesn't exist or something is wrong!");
        } else {
            while (input.hasNextLine()) {
                content = input.readLine();
                int wayNumber = Integer.parseInt(content);
                for (int i = 0; i < wayNumber; i++) {
                    content = input.readLine();
                    String[] inputWay = content.split(",", 7);
                    //System.out.println("Id: " + inputWay[0] + " || NodeInicial: " + inputWay[1] + " || NodeFinal: " + inputWay[2] + " || Peso: " + inputWay[3]);
                    Way way = new Way(Integer.parseInt(inputWay[0]), Integer.parseInt(inputWay[1]), Integer.parseInt(inputWay[2]), Double.parseDouble(inputWay[3]),Double.parseDouble(inputWay[4]));
                    DataBase.insertWayST(way);
                    if (Integer.parseInt(inputWay[5]) != 0) {
                        String[] tags = inputWay[6].split(",", Integer.parseInt(inputWay[5]));
                        way.tagArray.addAll(Arrays.asList(tags));
                        for (int j = 0; j < way.tagArray.size(); j += 2) {
                            way.tagST.put(way.tagArray.get(j), way.tagArray.get(j + 1));
                        }
                    }
                    /*for(String s : way.tagST.keys()){
                        System.out.println("Key : " + s + " || Value: " + way.tagST.get(s));
                    }*/
                }

            }

        }
    }

    public static void writeNodesToFile() {
        Out out = new Out(".//data//OutputNodes.txt");
        out.println(DataBase.poiST.size());
        for (int nodeID : DataBase.nodeST.keys()) {
            out.print(nodeID + "," + DataBase.nodeST.get(nodeID).getName() + "," + DataBase.nodeST.get(nodeID).getLocation().toStringtxt() + "," + DataBase.nodeST.get(nodeID).getType().toStringTXT());
            if (DataBase.poiST.get(nodeID).tagST.size() == 0) {
                out.print(",0");
            } else {
                out.print("," + DataBase.poiST.get(nodeID).tagArray.size());
                for (String s : DataBase.poiST.get(nodeID).tagST.keys()) {
                    out.print("," + s + "," + DataBase.poiST.get(nodeID).tagST.get(s));
                }
            }
            out.println();
        }
    }

    public static void writeWaysToFile() {
        Out out = new Out(".//data//OutputWays.txt");
        out.println(DataBase.wayST.size());
        for (int wayID : DataBase.wayST.keys()) {
            out.print(wayID + "," + DataBase.wayST.get(wayID).getIdNodeInicial() + "," + DataBase.wayST.get(wayID).getIdNodeFinal() + "," + DataBase.wayST.get(wayID).getDistance() + "," + DataBase.wayST.get(wayID).getAverageTime());
            if (DataBase.wayST.get(wayID).tagST.size() == 0) {
                out.print(",0");
            } else {
                out.print("," + DataBase.wayST.get(wayID).tagArray.size());
                for (String s : DataBase.wayST.get(wayID).tagST.keys()) {
                    out.print("," + s + "," + DataBase.wayST.get(wayID).tagST.get(s));
                }
            }
            out.println();
        }
    }

    public static void outputGrafo(EdgeWeightedDigraphProj grafo){
        Out out = new Out(".//data//OutputGrafo.txt");
        out.println(grafo.V());
        for(DirectedEdgeProj edge : grafo.edges()) {
            out.println(edge.from() + " -> " + edge.to() + " : " + edge.getDistance() + " || " + edge.getAverageTime());
        }
    }

    public static void outputGrafoBin(EdgeWeightedDigraphProj grafo){
        File f = new File(".//data//OutputGrafo.bin");
        try{
            FileOutputStream fout = new FileOutputStream(f);
            ObjectOutputStream oout = new ObjectOutputStream(fout);
            oout.writeObject(grafo);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static EdgeWeightedDigraphProj inputGrafoBin() {
        EdgeWeightedDigraphProj GrafoBin = null;
        File f = new File(".//data//OutputGrafo.bin");
        try {
            FileInputStream fin = new FileInputStream(f);
            ObjectInputStream oin = new ObjectInputStream(fin);
            GrafoBin = (EdgeWeightedDigraphProj) oin.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return GrafoBin;
    }
}
