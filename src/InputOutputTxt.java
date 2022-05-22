import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import javax.xml.crypto.Data;
import java.util.Arrays;

public class InputOutputTxt {

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
                    String[] inputNode = content.split(",", 5);
                    System.out.println("Id: " + inputNode[0] + " Lat: " + inputNode[1] + " Long: " + inputNode[2]);
                    Node node = new Node(Integer.parseInt(inputNode[0]), Double.parseDouble(inputNode[1]), Double.parseDouble(inputNode[2]));
                    DataBase.insertNodeST(node);
                    if (Integer.parseInt(inputNode[3]) != 0) {
                        String[] tags = inputNode[4].split(",", Integer.parseInt(inputNode[3]));
                        node.tagArray.addAll(Arrays.asList(tags));
                        for (int j = 0; j < node.tagArray.size(); j += 2) {
                            node.tagST.put(node.tagArray.get(j), node.tagArray.get(j + 1));
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
                    String[] inputWay = content.split(",", 6);
                    System.out.println("Id: " + inputWay[0] + " || NodeInicial: " + inputWay[1] + " || NodeFinal: " + inputWay[2] + " || Peso: " + inputWay[3]);
                    Way way = new Way(Integer.parseInt(inputWay[0]), Integer.parseInt(inputWay[1]), Integer.parseInt(inputWay[2]), Double.parseDouble(inputWay[3]));
                    DataBase.insertWayST(way);
                    if (Integer.parseInt(inputWay[4]) != 0) {
                        String[] tags = inputWay[5].split(",", Integer.parseInt(inputWay[4]));
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
        out.println(DataBase.nodeST.size());
        for (int nodeID : DataBase.nodeST.keys()) {
            out.print(nodeID + "," + DataBase.nodeST.get(nodeID).getLatitude() + "," + DataBase.nodeST.get(nodeID).getLongitude());
            if (DataBase.nodeST.get(nodeID).tagST.size() == 0) {
                out.print(",0");
            } else {
                out.print("," + DataBase.nodeST.get(nodeID).tagArray.size());
                for (String s : DataBase.nodeST.get(nodeID).tagST.keys()) {
                    out.print("," + s + "," + DataBase.nodeST.get(nodeID).tagST.get(s));
                }
            }
            out.println();
        }
    }

    public static void writeWaysToFile(){
        Out out = new Out(".//data//OutputWays.txt");
        out.println(DataBase.wayST.size());
        for(int wayID : DataBase.wayST.keys()){
            out.print(wayID + "," + DataBase.wayST.get(wayID).getIdNodeInicial() + "," + DataBase.wayST.get(wayID).getIdNodeFinal() + "," + DataBase.wayST.get(wayID).getWeight());
            if(DataBase.wayST.get(wayID).tagST.size() == 0) {
                out.print(",0");
            } else {
                out.print("," + DataBase.wayST.get(wayID).tagArray.size());
                for(String s : DataBase.wayST.get(wayID).tagST.keys()){
                    out.print("," + s + "," + DataBase.wayST.get(wayID).tagST.get(s));
                }
            }
            out.println();
        }
    }
}
