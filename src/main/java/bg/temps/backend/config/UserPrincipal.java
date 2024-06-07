//package bg.temps.backend.config;
//
//import bg.temps.backend.entities.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//public class UserPrincipal implements UserDetails {
//
//    private Long id;
//    private String username;
//    private String email;
//    private String password;
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public UserPrincipal(Long id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//    public static UserPrincipal build(User user) {
//        return new UserPrincipal(
//                user.getId(),
//                user.getUserName(),
//                user.getEmail(),
//                user.getPassword(),
//                user.getAuthorities()
//        );
//    }
//
//    // Override methods from UserDetails interface
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    // Additional methods
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}