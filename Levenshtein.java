

public interface Levenshtein{
    public int levDistance(String key,String work);

    public int levDistanceDp(String key,String work, int keylen, int worklen);

    public String wordSuggestion(String work);

}