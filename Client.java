public class Client {
    private String ID_client;
    private String nom;
    private String prenom;

    public Client(String iD_client, String nom, String prenom) {
        ID_client = iD_client;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getID_client() {
        return ID_client;
    }

    public void setID_client(String iD_client) {
        ID_client = iD_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void AfficherClient(){
        System.out.println("Client "+nom+" "+prenom+" | ID: "+ID_client);
    }

}