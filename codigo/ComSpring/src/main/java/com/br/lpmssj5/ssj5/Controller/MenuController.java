package com.br.lpmssj5.ssj5.Controller;

import com.br.lpmssj5.ssj5.Model.Menu;
import com.br.lpmssj5.ssj5.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu getMenuById(int id) {
        return menuRepository.findById(id).orElse(null);
    }

    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public void deleteMenu(int id) {
        menuRepository.deleteById(id);
    }
}
