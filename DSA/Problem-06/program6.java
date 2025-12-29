import java.util.*;
public class program6{

    static List<List<Integer>> graph = List.of(
            List.of(1,2),
            List.of(3),
            List.of(4),
            List.of(),
            List.of()
    );

    static void bfs(int start){
        boolean[] visited = new boolean[5];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int node = queue.poll();
            System.out.println("visited:" + node);

            for(int next: graph.get(node)){
                if(!visited[next]){
                    System.out.println("queue" + next + "from" +node );
                    visited[next]=true;
                    queue.add(next);
                }
            }
        }
    }

    static void dfs(int start, int end, List<Integer> path){
        path.add(start);

        if(start == end){
            System.out.println("path found:" + path);
        }else{
            for(int next : graph.get(start)){
                dfs(next, end, path);
            }
        }

        path.remove(path.size() -1 );

    }

    public static void main(String[] args) {
        bfs(0);
        dfs(0,3,new ArrayList<>());
    }
}

