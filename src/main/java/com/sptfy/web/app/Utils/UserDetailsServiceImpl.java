//package com.sptfy.web.app.Utils;
//
//import com.sptfy.web.app.Models.User;
//import com.sptfy.web.app.Repositories.UserRepository;
//import org.springframework.context.annotation.Role;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private UserRepository userRepository;
//
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = this.userRepository.findByEmail(username);
//        if (!user.isPresent())
//            throw new UsernameNotFoundException("User does not exists!");
//        return new UserData(user.get().getId(), user.get().getName(), user.get().getEmail(), user.get().getPassword(), user.get().isActive(), this.getAuthoritiesList(user.get().getRoles()));
//    }
//
//    private List<GrantedAuthority> getAuthoritiesList(Collection<Role> roles) {
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Role role : roles)
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        return grantedAuthorities;
//    }
//}
