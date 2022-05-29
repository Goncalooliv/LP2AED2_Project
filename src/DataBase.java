import edu.princeton.cs.algs4.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class DataBase implements Serializable {

    public static ST<Integer, User> userST = new ST<>();
    public static RedBlackBSTProj<Integer, Poi> poiST = new RedBlackBSTProj<>();
    //public static ST<String, Integer> subredeST = new ST<>();
    public static ArrayList<Log> allLogs = new ArrayList<>();
    public static ArrayList<Integer> poiCount = new ArrayList<>();

    public static RedBlackBSTProj<Integer, Node> nodeST = new RedBlackBSTProj<>();
    public static RedBlackBSTProj<Integer, Way> wayST = new RedBlackBSTProj<>();
    public static ArrayList<String> topPoiOrdered = new ArrayList<>();
    public static ArrayList<String> topUserOrdered = new ArrayList<>();
    public static ArrayList<String> top5Poi = new ArrayList<>();
    public static ArrayList<String> top5User = new ArrayList<>();

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
            for (int idUser : userST.keys()) {
                for (Date date : userST.get(idUser).visitedPoi.keys()) {
                    if (userST.get(idUser).visitedPoi.get(date).getIdPoi() == poi.getIdPoi()) {
                        userST.get(idUser).visitedPoi.delete(date);
                    }
                }
            }
            for (int nodeID : nodeST.keys()) {
                if (nodeST.get(nodeID).getId() == poi.getIdPoi()) {
                    nodeST.delete(nodeID);
                }
            }
            for(int wayID : wayST.keys()){
                if(wayST.get(wayID).getIdNodeInicial() == poi.getIdPoi() || wayST.get(wayID).getIdNodeFinal() == poi.getIdPoi()){
                    wayST.delete(wayID);
                }
            }
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
        System.out.println("\nPoi/Node Symbol Table:\n");
        for (int id : poiST.keys()) {
            System.out.println("Id: " + poiST.get(id).getIdPoi() + " || Name: " + poiST.get(id).getPoiName() + " || " + poiST.get(id).getLocation().toString());
            for (String s : poiST.get(id).tagST.keys()) {
                System.out.println("Key : " + s + " || Value: " + poiST.get(id).tagST.get(s));
            }
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

    /**
     * Imprime um estado atual da app, mais concretamente, imprime os POIs existentes, e em baixo a lista total de users que já os visitaram
     * nao tendo em conta a data da visita.
     */
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
                System.out.println("\n");
            }
        }
    }

    /**
     * adiciona logs à ST de logs dos POIs bem como nos Logs Globais
     *
     * @param log a adicionar
     * @param p   a que pertence o log
     */
    public static void addLog(Log log, Poi p) {
        p.poiLog.put(log.getDate(), log);
        allLogs.add(log);
    }

    /**
     * Imprime os 5 POIs que mais visitas tiveram, ordenados de forma decrescente
     * @param dataInicial
     * @param dataFinal
     */
    public static void top5PoiUsedPeriod(Date dataInicial, Date dataFinal) {
        int i = 0;
        for (int poiID : poiST.keys()) {
            topPoiOrdered.add("Visitas: " + poiST.get(poiID).poiLog.size() + " " + poiST.get(poiID).getPoiName());
        }
        topPoiOrdered.sort(Collections.reverseOrder());
        System.out.println(topPoiOrdered);

        while (i != 5) {
            top5Poi.add(i, topPoiOrdered.get(i));
            i++;
        }

        System.out.println("Top 5 POIs mais visitados: " + top5Poi);
        System.out.println("===============ARRAY==============");
    }

    /**
     * Imprime os 5 Users que visitaram um maior numero de POIs, ordenados de forma decrescente
     * @param dataInicial
     * @param dataFinal
     */
    public static void top5UsersVisitedPoi(Date dataInicial, Date dataFinal) {
        int i = 0;
        for (int userID : userST.keys()) {
            topUserOrdered.add("Visitou: " + userST.get(userID).visitedPoi.size() + " pois! User: " + userST.get(userID).getName());
        }

        topUserOrdered.sort(Collections.reverseOrder());
        System.out.println(topUserOrdered);

        while (i != 5) {
            top5User.add(i, topUserOrdered.get(i));
            i++;
        }

        System.out.println("Top 5 Users que visitaram mais POIs: " + top5User);
        System.out.println("===============ARRAY==============");
    }

    /**
     * Metodo usado apenas para aspeto visual, que imprime o intervalo de tempo das pesquisas do requisito 5
     *
     * @param dataInicial do periodo de tempo da pesquisa
     * @param dataFinal   do periodo de tempo da pesquisa
     * @param subrede     que o user pretende pesquisar
     */
    public static void printIntervaloTempo(Date dataInicial, Date dataFinal, String subrede) {
        System.out.println("No periodo de tempo: " + dataInicial.toString() + " - " + dataFinal.toString() + " || Subrede: " + subrede);
    }

    //=======================NODE====================//

    /**
     * Metodo para inserir Nodes na ST
     * Verifica se o ID já existe, caso exista
     *
     * @param node a inserir na ST
     */
    public static void insertNodeST(Node node) {
        if (nodeST.contains(node.getId())) {
            System.out.println("Node with id " + node.getId() + " already exists!");
            return;
        }
        nodeST.put(node.getId(), node);
    }

    /**
     * Metodo usado para imprimir a ST de Nodes na consola
     */
    public static void printNodeST() {
        System.out.println("\nNode Symbol Table:\n");
        for (int id : nodeST.keys()) {
            System.out.println("Id: " + nodeST.get(id).getId() + "Location: " + nodeST.get(id).getLocation().toStringtxt());
            for (String s : nodeST.get(id).tagST.keys()) {
                System.out.println("Key : " + s + " || Value: " + nodeST.get(id).tagST.get(s));
            }
        }
    }

    /**
     * Metodo usado para remover um Node da ST
     * E para assegurar uma coerencia no sistema vê tambem se o node que queremos remover está presente na ST de Ways
     * Caso esteja presente remove-mos tambem da ST das Ways, uma vez que não há uma way entre um node X e um node nao existente
     *
     * @param node que pretendemos remover da ST
     */
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

    /**
     * Edita os atributos dos Nodes Existentes
     * @param node que pretendemos alterar
     * @param poiName a alterar
     * @param location a alterar
     * @param type a alterar
     */
    public static void editNodeST(Node node, String poiName, Location location, PoiType type) {
        if (poiName != null) {
            node.setName(poiName);
        }
        if (location != null) {
            node.setLocation(location);
        }
        if (type != null) {
            node.setType(type);
        }
        nodeST.put(node.getId(), node);
        poiST.put(node.getId(), (Poi) node);
    }

    /**
     * Metodo usado para pesquisar se existem nodes na ST com uma Tag escolhida pelo user
     * Exemplo: tag = highway
     * vai imprimir todos os Nodes que possuam na lista de tags a tag "highway"
     *
     * @param tag a pesquisar
     */
    public static void searchTagInNode(String tag) {
        for (int nodeID : poiST.keys()) {
            for (String s : poiST.get(nodeID).tagST.keys()) {
                if (poiST.get(nodeID).tagST.get(s).contains(tag)) {
                    System.out.println("Node: " + nodeID + " has a tag containing what you searched!");
                    System.out.println("Tag: " + s + " || Value: " + tag);
                }
            }
            if (poiST.get(nodeID).tagST.contains(tag)) {
                System.out.println("Node: " + nodeID + " has a tag containing what you searched!");
                System.out.println("Tag: " + tag + " || Value: " + poiST.get(nodeID).tagST.get(tag));
            }

        }

    }

    /**
     * Pesquisa pelo tipo de poi nos nodes lidos do ficheiro de texto
     * @param type
     */
    public static void searchTypeInNode(String type) {
        for (int nodeID : nodeST.keys()) {
            if (nodeST.get(nodeID).getType().toStringTXT().equalsIgnoreCase(type)) {
                System.out.println("Node: " + nodeID + " has what you searched for!");
                System.out.println("-> " + type);
            }
        }
    }

    //=======================WAY====================//

    /**
     * Metodo para inserir Ways na ST
     * Caso o ID já exista, nao adiciona na ST
     *
     * @param way a adicionar na ST
     */
    public static void insertWayST(Way way) {
        if (wayST.contains(way.getId())) {
            System.out.println("Way with id " + way.getId() + " already exists!");
            return;
        }
        wayST.put(way.getId(), way);
    }

    /**
     * Metodo usado para pesquisar se existem ways na ST com uma Tag escolhida pelo user
     * Exemplo: tag = oneway
     * vai imprimir todos os ways que possuam na lista de tags a tag "oneway"
     *
     * @param tag a pesquisar
     */
    public static void searchTagInWay(String tag) {
        for (int wayID : wayST.keys()) {
            for (String s : wayST.get(wayID).tagST.keys()) {
                if (wayST.get(wayID).tagST.get(s).contains(tag)) {
                    System.out.println("Way: " + wayID + " has the tag you searched!");
                    System.out.println("Tag: " + s + " || Value: " + tag);
                }
            }
            if (wayST.get(wayID).tagST.contains(tag)) {
                System.out.println("Way: " + wayID + " has a tag containing what you searched!");
                System.out.println("Tag: " + tag + " || Value: " + wayST.get(wayID).tagST.get(tag));
            }
        }
    }

    /**
     * Usado no controller para dar clear à ST de users (para nao ler repetido)
     */
    public static void clearUser() {
        for (int userID : userST.keys()) {
            deleteUserST(userST.get(userID));
        }
    }

    /**
     * Usado no controller para dar clear à ST de POIs(para nao ler repetido)
     */
    public static void clearPoi() {
        for (int poiID : poiST.keys()) {
            deletePoiST(poiST.get(poiID));
        }
    }

    /**
     * Método para calcular o shortest path entre um vertice e outro
     * @param v1
     * @param v2
     * @param ed
     */
    public static void shortestPath(int v1, int v2, EdgeWeightedDigraphProj ed) {
        DijkstraSPProj dijkstra = new DijkstraSPProj(ed, v1, 2);

        if (dijkstra.hasPathTo(v2)) {
            if (dijkstra.getType() == 1) {
                for (DirectedEdgeProj e : dijkstra.pathTo(v2)) {
                    System.out.println(e);
                }
                System.out.println("Do vertice " + v1 + " para o vertice " + v2 + " o caminho mais curto tem: " + dijkstra.distTo(v2) + " metros!");
            } else if (dijkstra.getType() == 2) {
                for (DirectedEdgeProj e : dijkstra.pathTo(v2)) {
                    System.out.println(e);
                }
                System.out.println("Para ir do vertice " + v1 + " para o vertice " + v2 + " precisamos de: " + dijkstra.distTo(v2) + " minutos!");
            }

        } else {
            System.out.println(v1 + " -> " + v2);
            System.out.println("Nao conseguimos chegar a esse vertice a partir do vertice inicial dado");
        }
    }

}
