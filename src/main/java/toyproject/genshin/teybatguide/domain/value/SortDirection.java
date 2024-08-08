package toyproject.genshin.teybatguide.domain.value;

public enum SortDirection {

    ASCENDING, DESCENDING;


    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
