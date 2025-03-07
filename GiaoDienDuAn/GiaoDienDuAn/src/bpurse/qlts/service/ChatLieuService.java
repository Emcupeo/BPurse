/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bpurse.qlts.service;

import bpurse.qlts.entity.ChatLieu;
import bpurse.qlts.repo.ChatLieuRepo;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ChatLieuService {
    private ChatLieuRepo repo = new ChatLieuRepo();
    public List<ChatLieu> getTenCL (){
        return repo.getTenCL();
    }
}
