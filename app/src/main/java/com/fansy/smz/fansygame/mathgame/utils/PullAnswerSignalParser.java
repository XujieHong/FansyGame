package com.fansy.smz.fansygame.mathgame.utils;

/**
 * Created by hongxujie on 1/19/18.
 */

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class PullAnswerSignalParser implements SignalParser {

    @Override
    public List<AnswerSignal> parse(String xmlStr) throws Exception {
        List<AnswerSignal> answerSignals = null;
        AnswerSignal answerSignal = null;

        XmlPullParser parser = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例
        parser.setInput(new StringReader(xmlStr));               //设置输入流 并指明编码方式

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    answerSignals = new ArrayList<AnswerSignal>();
                    break;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("answerSignal")) {
                        answerSignal = new AnswerSignal();
                    } else if (parser.getName().equals("hour")) {
                        eventType = parser.next();
                        answerSignal.setHour(Integer.parseInt(parser.getText()));
                    } else if (parser.getName().equals("minute")) {
                        eventType = parser.next();
                        answerSignal.setMinute(Integer.parseInt(parser.getText()));
                    } else if (parser.getName().equals("second")) {
                        eventType = parser.next();
                        answerSignal.setSecond(Integer.parseInt(parser.getText()));
                    } else if (parser.getName().equals("millisecond")) {
                        eventType = parser.next();
                        answerSignal.setMillisecond(Integer.parseInt(parser.getText()));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("answerSignal")) {
                        answerSignals.add(answerSignal);
                        answerSignal = null;
                    }
                    break;
            }
            eventType = parser.next();
        }
        return answerSignals;
    }

    @Override
    public String serialize(List<AnswerSignal> answerSignals) throws Exception {

        XmlSerializer serializer = Xml.newSerializer(); //由android.util.Xml创建一个XmlSerializer实例
        StringWriter writer = new StringWriter();
        serializer.setOutput(writer);   //设置输出方向为writer
        serializer.startDocument("UTF-8", true);
        serializer.startTag("", "answerSignals");
        for (AnswerSignal answerSignal : answerSignals) {
            serializer.startTag("", "answerSignal");
            serializer.startTag("", "hour");
            serializer.text(answerSignal.getHour() + "");
            serializer.endTag("", "hour");
            serializer.startTag("", "minute");
            serializer.text(answerSignal.getMinute() + "");
            serializer.endTag("", "minute");
            serializer.startTag("", "second");
            serializer.text(answerSignal.getSecond() + "");
            serializer.endTag("", "second");
            serializer.startTag("", "millisecond");
            serializer.text(answerSignal.getMillisecond() + "");
            serializer.endTag("", "millisecond");
            serializer.endTag("", "answerSignal");
        }
        serializer.endTag("", "answerSignals");
        serializer.endDocument();

        return writer.toString();
    }
}