import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {

    public static LinearProbingHashST<Integer, User> userST = new LinearProbingHashST<>();
    public static RedBlackBST<Integer, Poi> poiST = new RedBlackBST<>();
    public static ST<String, Integer> subredeST = new ST<>();
    public static ArrayList<Log> allLogs = new ArrayList<>();
    public static ArrayList<Integer> poiCount = new ArrayList<>();

    public static RedBlackBST<Integer, Node> nodeST = new RedBlackBST<>();
    public static RedBlackBST<Integer, Way> wayST = new RedBlackBST<>();

    //================================================USER==========================================//
    //Requisito 3 (Inserir,Remover,Editar,Listar,Pesquisar)

    /**
     * Inserir um User na ST
     * Verifica se o ID ja existe, caso exista dá ao User o próximo id Disponivel
     *
     * @param u user a inserir na st
     */
    public static void insertUserST(User u) {
        int id = 1;
        if (userST.contains(u.getId())) {
            System.out.println("Id " + u.getId() + " is already being used by other user and will be changed to the next available one");
        }
        while (userST.contains(u.getId()) || u.getId() == 0) {
            u.setId(id);
            id++;
        }
        userST.put(u.getId(), u);
    }

    /**
     * Remover um User da ST
     * Verifica se o user existe na ST, caso exista remove-o da ST
     *
     * @param u user a remover
     */
    public static void deleteUserST(User u) {
        if (userST.contains(u.getId())) {
            userST.delete(u.getId());
            System.out.println("User with id: " + u.getId() + " || Nome: " + u.getName() + ", vai ser eliminado da ST!");
        } else {
            System.out.println("The Symbol Table doesn't have any user with the ID: " + u.getId());
        }
    }

    /**
     * Edita os atributos de um User existente
     *
     * @param u    user a editar
     * @param nome que irá ou nao trocar o anterior
     * @param type de user que irá ou nao trocar o anterior
     */
    public static void editUserST(User u, String nome, Type type) {
        if (nome != null) {
            u.setName(nome);
        }
        if (type != null) {
            u.setType(type);
        }
        userST.put(u.getId(), u);
    }

    /**
     * Percorre a ST de Users e imprime todos os Users existentes na ST
     */
    public static void printUserST() {
        System.out.println("\nUser Symbol Table:\n");
        for (int id : userST.keys()) {
            System.out.println("Id: " + userST.get(id).getId() + "\tName: " + userST.get(id).getName() + "\tType: " + userST.get(id).getType());
        }
    }

    /**
     * A partir de um id previamente definido, pesquisa na ST por um user que possua este id
     * Caso haja um user com o id definido, retorna a informação do mesmo
     *
     * @param id do user a pesquisar
     */
    public static void searchUserST(int id) {
        if (userST.contains(id)) {
            System.out.println("\nFound a user with that id!");
            System.out.println("Id: " + userST.get(id).getId() + " || Name: " + userST.get(id).getName() + " || Type: " + userST.get(id).getType());
        } else {
            System.out.println("There's no user with that id (" + id + ")!");
        }
    }

    //===============================================POI===================================================//
    //Requisito 3 (Inserir,Remover,Editar,Listar,Pesquisar)

    /**
     * Inserir um Poi na ST
     * Verifica se o ID ja existe, caso exista dá ao POI o próximo id Disponivel
     *
     * @param poi a inserir na ST
     */
    public static void insertPoiST(Poi poi) {
        int id = 1;
        if (poiST.contains(poi.getIdPoi())) {
            System.out.println("Id " + poi.getIdPoi() + " is already being used by other user and will be changed to the next available one");
        }
        while (poiST.contains(poi.getIdPoi()) || poi.getIdPoi() == 0) {
            poi.setIdPoi(id);
            id++;
        }
        poiST.put(poi.getIdPoi(), poi);
    }

    /**
     * Remover um POI da ST
     * Verifica se o POI existe na ST, caso exista remove-o da ST
     *
     * @param poi a remover da ST
     */
    public static void deletePoiST(Poi poi) {
        if (poiST.contains(poi.getIdPoi())) {
            poiST.delete(poi.getIdPoi());
            System.out.println("Poi with id: " + poi.getIdPoi() + " || Nome: " + poi.getPoiName() + ", vai ser eliminado da ST!");
        } else {
            System.out.println("The Symbol Table doesn't have any Poi with the ID: " + poi.getIdPoi());
        }
    }

    /**
     * Edita os atributos de um POI existente
     *
     * @param poi      a editar
     * @param poiName  contem o novo nome para o poi
     * @param location contem a nova location para o poi
     * @param poiType  contem o novo POItype para o poi
     * @param details  contem os detalhes do Poi editado
     */
    public static void editPoiST(Poi poi, String poiName, Location location, PoiType poiType, String details) {
        if (poiName != null) {
            poi.setPoiName(poiName);
        }
        if (location != null) {
            poi.setLocation(location);
        }
        if (poiType != null) {
            poi.setPoiType(poiType);
        }
        if (details != null) {
            poi.setDetails(details);
        }
        poiST.put(poi.getIdPoi(), poi);
    }

    /**
     * Percorre a ST de POIs e imprime todos os POIs existentes na ST
     */
    public static void printPoiST() {
        System.out.println("\nPoi Symbol Table:\n");
        for (int id : poiST.keys()) {
            System.out.println("Id: " + poiST.get(id).getIdPoi() + " || Name: " + poiST.get(id).getPoiName() + " || " + poiST.get(id).getLocation() + poiST.get(id).getPoiType());
            System.out.println("Details: " + poiST.get(id).getDetails() + "\n");
            /*if(poiST.get(id).poiLog.size() > 0){
                System.out.println("Logs: ");
                for(Date date : poiST.get(id).poiLog.keys()){
                    System.out.println(poiST.get(id).poiLog.get(date).toString());
                }
            }*/
        }
    }

    /**
     * A partir de um id previamente definido, pesquisa na ST por um POI que possua este id
     * Caso haja um user com o id definido, retorna a informação do mesmo
     *
     * @param idPoi do poi a pesquisar
     */
    public static void searchPoiST(int idPoi) {
        if (poiST.contains(idPoi)) {
            System.out.println("Found a POI with that id!");
            System.out.println("ID: " + poiST.get(idPoi).getIdPoi() + " || Name: " + poiST.get(idPoi).getPoiName() + " || Location: " + poiST.get(idPoi).getLocation());
        } else {
            System.out.println("There's no POI with that id(" + idPoi + ")!\n");
        }
    }


    public static void now() {
        for (int poiID : poiST.keys()) {
            System.out.println("ID : " + poiST.get(poiID).getIdPoi() + " || Nome: " + poiST.get(poiID).getPoiName() + " || Location: " + poiST.get(poiID).getLocation().toString());
            ST<Integer, User> user = poiST.get(poiID).allUsersThatVisited();
            if (user.size() == 0) {
                System.out.println("\nNenhum user visitou este POI\n");
            } else {
                System.out.println("\nUsers que visitaram: ");
                for (int id : user.keys()) {
                    System.out.println("Nome: " + user.get(id).getName() + " || Type: " + user.get(id).getType());
                }
                System.out.println("");
            }
        }
    }

    /*public static void now(){
        for(int poiID : poiST.keys()){
            System.out.println("ID : " + poiST.get(poiID).getIdPoi() + " || Nome: " + poiST.get(poiID).getPoiName() + " || Location: " + poiST.get(poiID).getLocation().toString());

        }
    }*/

    public static void addLog(Log log, Poi p) {
        p.poiLog.put(log.getDate(), log);
        allLogs.add(log);
    }

    /*public static void top5PoiUsedPeriod(Date dataInicial, Date dataFinal){
        int count = 0;
        for(int poiID : poiST.keys()){
            for(Date data : poiST.get(poiID).poiLog.keys()){
                if(dataInicial.beforeDate(data) && dataFinal.afterDate(data)){
                    count++;
                }
            }
            System.out.println("Poi com id: " + poiID + " foi visitado: " + count + " vezes no periodo definido");
            poiCount.add(count);
            count = 0;
        }
    }*/

    public static void printIntervaloTempo(Date dataInicial, Date dataFinal, String subrede) {
        System.out.println("No periodo de tempo: " + dataInicial.toString() + " - " + dataFinal.toString() + " || Subrede: " + subrede);
    }

    //=======================NODE====================//

    public static void insertNodeST(Node node) {
        if (nodeST.contains(node.getId())) {
            System.out.println("Node with id " + node.getId() + " already exists!");
            return;
        }
        nodeST.put(node.getId(), node);
    }

    public static void printNodeST() {
        System.out.println("\nNode Symbol Table:\n");
        for (int id : nodeST.keys()) {
            System.out.println("Id: " + nodeST.get(id).getId() + "\tLatitude: " + nodeST.get(id).getLatitude() + "\tLongitude: " + nodeST.get(id).getLongitude());
            for (String s : nodeST.get(id).tagST.keys()) {
                System.out.println("Key : " + s + " || Value: " + nodeST.get(id).tagST.get(s));
            }
        }
    }

    public static void removeNodeST(Node node) {
        if (nodeST.contains(node.getId())) {
            System.out.println("Vai ser removido o node com id: " + node.getId());
            for (String s : nodeST.get(node.getId()).tagST.keys()) {
                nodeST.get(node.getId()).tagST.delete(s);
            }
            for (int wayID : wayST.keys()) {
                if (wayST.get(wayID).getIdNodeInicial() == node.getId() || wayST.get(wayID).getIdNodeFinal() == node.getId()) {
                    wayST.delete(wayID);
                }
            }
            nodeST.delete(node.getId());
        } else {
            System.out.println("Este node não existe na ST");
        }
    }

    //=======================WAY====================//

    public static void insertWayST(Way way) {
        if (wayST.contains(way.getId())) {
            System.out.println("Way with id " + way.getId() + " already exists!");
            return;
        }
        wayST.put(way.getId(), way);
    }

}
