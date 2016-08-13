package kr.corearoad.bean;

import kr.corearoad.util.CommonUtils;

import java.util.List;

/**
 * Created by Naver on 2016-08-02.
 */
public class CoreaPicks {

        private String postId;
        private String category;
        private String tagList;
        private String registDate;
        private String modifiedDate;
        private String writerEmail;
        private String content;
        private boolean isSponsor;
        private String map;
        private String title;
        private int startPrice;
        private int endPrice;
        private String mainPicture;
        private List processedTagList;

        public String getPostId() {
                return postId;
        }

        public void setPostId(String postId) {
                this.postId = postId;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public String getTagList() {
                return tagList;
        }

        public void setTagList(String tagList) {
                this.tagList = tagList;
        }

        public String getRegistDate() {
                return registDate;
        }

        public void setRegistDate(String registDate) {
                this.registDate = registDate;
        }

        public String getModifiedDate() {
                return modifiedDate;
        }

        public void setModifiedDate(String modifiedDate) {
                this.modifiedDate = modifiedDate;
        }

        public String getWriterEmail() {
                return writerEmail;
        }

        public void setWriterEmail(String writerEmail) {
                this.writerEmail = writerEmail;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public boolean getIsSponsor() {
                return isSponsor;
        }

        public void setIsSponsor(boolean isSponsor) {
                this.isSponsor = isSponsor;
        }

        public String getMap() {
                return map;
        }

        public void setMap(String map) {
                this.map = map;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public int getStartPrice() {
                return startPrice;
        }

        public void setStartPrice(int startPrice) {
                this.startPrice = startPrice;
        }

        public int getEndPrice() {
                return endPrice;
        }

        public void setEndPrice(int endPrice) {
                this.endPrice = endPrice;
        }

        public String getMainPicture() {
                return mainPicture;
        }

        public void setMainPicture(String mainPicture) {
                this.mainPicture = mainPicture;
        }

        public void setProcessedTagList() {
                this.processedTagList = CommonUtils.returnStringArrayFromString(getTagList());
        }

        @Override
        public String toString() {
                final StringBuffer sb = new StringBuffer("CoreaPicks{");
                sb.append("postId='").append(postId).append('\'');
                sb.append(", category='").append(category).append('\'');
                sb.append(", tagList='").append(tagList).append('\'');
                sb.append(", registDate='").append(registDate).append('\'');
                sb.append(", modifiedDate='").append(modifiedDate).append('\'');
                sb.append(", writerEmail='").append(writerEmail).append('\'');
                sb.append(", content='").append(content).append('\'');
                sb.append(", isSponsor=").append(isSponsor);
                sb.append(", map='").append(map).append('\'');
                sb.append(", title='").append(title).append('\'');
                sb.append(", startPrice=").append(startPrice);
                sb.append(", endPrice=").append(endPrice);
                sb.append(", mainPicture='").append(mainPicture).append('\'');
                sb.append(", processedTagList=").append(processedTagList);
                sb.append('}');
                return sb.toString();
        }
}
