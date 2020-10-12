package oit.is.z1534.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Lec02Controller {

  /**
   * POSTを受け付ける場合は@PostMappingを利用する /sample25へのpostを受け付けて，FormParamで指定された変数(input
   * name)をsample25()メソッドの引数として受け取ることができる
   *
   * @param kakeru1
   * @param model
   * @return
   */
  @PostMapping("/lec02")
  public String sample25(@RequestParam String kakeru1, ModelMap model) {
    String para = kakeru1;
    model.addAttribute("name", para);
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
