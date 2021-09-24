import java.util.ArrayList;

/**
 * The main class of ass3.
 * @author Yotam Levin
 * ID: 313248916
 */
public class Ass6Game {

    /**
     * The main of ass6.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        ArrayList<LevelInformation> li = new ArrayList<>();
        GameFlow gf = new GameFlow();
        if (args.length == 0 || args[0].equals("${args}")) {
            li.add(new DirectHitLevel());
            li.add(new WideEasyLevel());
            li.add(new Green3Level());
            li.add(new FinalFourLevel());
        } else {
            for (String arg : args) {
                if (arg.equals("1")) {
                    li.add(new DirectHitLevel());
                } else if (arg.equals("2")) {
                    li.add(new WideEasyLevel());
                } else if (arg.equals("3")) {
                    li.add(new Green3Level());
                } else if (arg.equals("4")) {
                    li.add(new FinalFourLevel());
                }
            }
        }
        gf.runLevels(li);
    }
}
