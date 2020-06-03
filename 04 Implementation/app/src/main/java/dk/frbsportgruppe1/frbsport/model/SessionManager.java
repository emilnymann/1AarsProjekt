package dk.frbsportgruppe1.frbsport.model;

/**
 * Singleton til at opbevare en instans af den indloggede bruger.
 */
public class SessionManager {
    private static SessionManager instance = null;

    private User currentUser;

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
