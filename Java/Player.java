package Java;
abstract class Player {
    public abstract Cell[] move(Cell[] board);
    private String token;
    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
    }
}