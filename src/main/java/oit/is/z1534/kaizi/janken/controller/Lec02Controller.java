package oit.is.z1534.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
   * パスパラメータ2つをGETで受け付ける 1つ目の変数をparam1という名前で，2つ目の変数をparam2という名前で受け取る
   * GETで受け取った2つの変数とsample22の引数の名前が同じなため， 引数の前に @PathVariable と付けるだけで，パスパラメータの値を
   * javaで処理できるようになる ModelMapはthymeleafに渡すためのMapと呼ばれるデータ構造を持つ変数
   * Mapはkeyとvalueの組み合わせで値を保持する
   *
   * @param param1
   * @param model1
   * @param model2
   * @return
   */
  @GetMapping("/sample22/{param1}")
  public String sample22(@PathVariable Integer param1, ModelMap model1,ModelMap model2) {
    if(param1 > 2){
      model1.addAttribute("YourHand", "Pa");
      model2.addAttribute("Result", "You Win!!");
    }else if(param1 < 2){
      model1.addAttribute("YourHand", "Chi");
      model2.addAttribute("Result", "You lose...");
    }else{
      model1.addAttribute("YourHand", "Gu");
      model2.addAttribute("Result", "-Draw-");
    }
    return "lec02.html";
  }

}
