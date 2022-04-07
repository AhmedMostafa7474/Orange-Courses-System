package com.example.Spring1.Service;

import com.example.Spring1.Interfaces.IAdminServices;
import com.example.Spring1.Model.Adminn;
import com.example.Spring1.Model.MyAdminDetails;
import com.example.Spring1.Repo.AdminnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminnServices implements IAdminServices , UserDetailsService {

    @Autowired
    AdminnRepo adminnRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public boolean AddAdmin(Adminn adminn) {
        try {
            adminn.setPassword(passwordEncoder.encode(adminn.getPassword()));
            adminnRepo.save(adminn);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deleteAdmin(int admin_id) {
        try {
            adminnRepo.deleteById(admin_id);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public List<Adminn> GetAdmins() {
        try {
           List<Adminn>adminns= (List<Adminn>) adminnRepo.findAll();
            return adminns;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Adminn> user= adminnRepo.findByUsername(s);
        return user.map(MyAdminDetails::new).get();
    }
}
