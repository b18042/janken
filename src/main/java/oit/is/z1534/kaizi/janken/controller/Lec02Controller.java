package oit.is.z1534.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1534.kaizi.janken.model.User;
import oit.is.z1534.kaizi.janken.model.UserMapper;
import oit.is.z1534.kaizi.janken.model.Match;
import oit.is.z1534.kaizi.janken.model.MatchMapper;


import oit.is.z1534.kaizi.janken.model.Entry;

@Controller
public class Lec02Controller {

  @Autowired
  private Entry entry;
  @Autowired
  UserMapper userMapper;
  @Autowired
  MatchMapper matchMapper;

  /**
   * POSTを受け付ける場合は@PostMappingを利用する /sample25へのpostを受け付けて，FormParamで指定された変数(input
   * name)をsample25()メソッドの引数として受け取ることができる
   *
   * @param prin
   * @param model
   * @return
   */
  @GetMapping("/lec02")
  public String sample25(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
        this.entry.addUser(loginUser);
          model.addAttribute("entry", this.entry);
      model.addAttribute("name", loginUser);
    ArrayList<User> userA = userMapper.selectAllUser();
      model.addAttribute("userA", userA);
    ArrayList<Match> matchA = matchMapper.selectAllMatch();
      model.addAttribute("matchA", matchA);
    return "lec02.html";
  }

  /**
   *  @param id
   *  @param prin
   *  @param model
   *  @param return
  */

  @GetMapping("/match")
  public String draw_match(@RequestParam Integer id, Principal prin, ModelMap model) {
    User vs_user = userMapper.selectById(id);
      model.addAttribute("vs_user",vs_user);
    String loginUser = prin.getName();
    User user = userMapper.selectByName(loginUser);
      model.addAttribute("user", user);
    return "match.html";
  }

  /**
   * パスパラメータ2つをGETで受け付ける 1つ目の変数をparam1という名前で，2つ目の変数をparam2という名前で受け取る
   * GETで受け取った2つの変数とsample22の引数の名前が同じなため， 引数の前に @PathVariable と付けるだけで，パスパラメータの値を
   * javaで処理できるようになる ModelMapはthymeleafに渡すためのMapと呼ばれるデータ構造を持つ変数
   * Mapはkeyとvalueの組み合わせで値を保持する
   *
   * @param param1
   * @param vs_userid
   * @param prin
   * @param model
   * @return
   */
  @GetMapping("/sample22/{param1}")
  @Transactional
  public String sample22(@PathVariable Integer param1, @RequestParam Integer vs_userid, Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    User vs_user = userMapper.selectById(vs_userid);
    User user = userMapper.selectByName(loginUser);
    Match ma_resu = new Match();
      int userid = user.getId();
      ma_resu.setUser_1(userid);
      ma_resu.setUser_2(vs_userid);
      ma_resu.setUser_2_hand("Gu");
    String result = "";
    if(param1 > 2){
      ma_resu.setUser_1_hand("Pa");
      result = "You Win!!";
    }else if(param1 < 2){
      ma_resu.setUser_1_hand("Choki");
      result = "You lose...";
    }else{
      ma_resu.setUser_1_hand("Gu");
      result = "-Draw-";
    }
    model.addAttribute("ma_resu", ma_resu);
    model.addAttribute("vs_user", vs_user);
    model.addAttribute("user", user);
    model.addAttribute("Result", result);
    matchMapper.insertMatch(ma_resu);
    return "match.html";
  }

}
