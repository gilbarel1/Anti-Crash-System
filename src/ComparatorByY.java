import java.util.Comparator;

public class ComparatorByY implements Comparator<Container> {

    @Override
    public int compare(Container c1, Container c2) {
        return ((Integer) c1.getData().getY()).compareTo((Integer) c2.getData().getY());
    }

}
