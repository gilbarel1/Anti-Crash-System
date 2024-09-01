import java.util.Comparator;

public class ComparatorByX implements Comparator<Container> {

    @Override
    public int compare(Container c1, Container c2) {
        return ((Integer)c1.getData().getX()).compareTo((Integer)c2.getData().getX());
    }
}
