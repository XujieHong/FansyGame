package com.fansy.smz.fansygame.mathgame.utils;

/**
 * Created by hongxujie on 1/19/18.
 */

import java.util.List;

public interface SignalParser {
    /**
     * 解析输入流 得到AnswerSignal对象集合
     * @param xmlStr
     * @return
     * @throws Exception
     */
    public List<AnswerSignal> parse(String xmlStr) throws Exception;

    /**
     * 序列化AnswerSignal对象集合 得到XML形式的字符串
     * @param answerSignals
     * @return
     * @throws Exception
     */
    public String serialize(List<AnswerSignal> answerSignals) throws Exception;
}