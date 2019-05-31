package com.libiyi.exa.admin.api.util;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvToBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * Created by bibi on 2019/5/30.
 */
public class ParseCSVUtil {

    public static List<QuestionBean> parseCSV2QuestionList(MultipartFile file, Integer userId) throws IOException, RuntimeException {
        CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream(),"gbk"));
        ColumnPositionMappingStrategy<QuestionBean> mapper = new ColumnPositionMappingStrategy<QuestionBean>();
        mapper.setType(QuestionBean.class);
        CsvToBean<QuestionBean> csvToBean = new CsvToBean<QuestionBean>();
        reader.readNext();
        List<QuestionBean> questionList = csvToBean.parse(mapper, reader);
        return questionList;
    }

    public static class QuestionBean{
        public QuestionBean() {
        }

        @CsvBindByPosition(position=0)
        private Integer type;

        @CsvBindByPosition(position=1)
        private Integer subjectId;

        @CsvBindByPosition(position=2)
        private String content;

        @CsvBindByPosition(position=3)
        private String options = "";

        @CsvBindByPosition(position=4)
        private String answer;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Integer subjectId) {
            this.subjectId = subjectId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOptions() {
            return options;
        }

        public void setOptions(String options) {
            this.options = options;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }


}
