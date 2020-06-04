package dk.frbsportgruppe1.frbsport.repository;

import com.google.firebase.auth.FirebaseUser;

public interface UserRepository {
    void signInSession(FirebaseUser user);
}
