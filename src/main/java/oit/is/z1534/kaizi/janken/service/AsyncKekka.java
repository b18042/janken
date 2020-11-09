package oit.is.z1534.kaizi.janken.service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z1534.kaizi.janken.model.Match;
import oit.is.z1534.kaizi.janken.model.MatchMapper;

@Service
public class AsyncKekka {
  boolean dbUpdated = false;

  private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

  @Autowired
  MatchMapper matchMapper;

  public Match syncSelectLastMatch() {
    return matchMapper.selectLastMatch();
  }

  /**
   * @param emitter
   */
  @Async
  public void asyncShowReturn(SseEmitter emitter) {
    dbUpdated = true;
    Match match = new Match();
    try {
      while (true) {
        TimeUnit.MILLISECONDS.sleep(500);
        match = syncSelectLastMatch();
        dbUpdated = match.getIs_active();
        if (false == dbUpdated) {
          continue;
        }
        int last_id = match.getId();
        emitter.send(last_id);
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
    System.out.println("asyncShowReturn complete");
  }

}
