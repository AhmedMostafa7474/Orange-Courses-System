package com.example.Spring1.Interfaces;

import com.example.Spring1.Model.Adminn;

import java.util.List;

public interface IAdminServices {
    public boolean AddAdmin(Adminn adminn);
    public boolean deleteAdmin(int admin_id);
    public List<Adminn>GetAdmins();
}
