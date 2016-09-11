package nalanda.com.naima.viewmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ps1 on 9/10/16.
 */

public class SymptomItemModel extends BaseViewModel {
    private String id;

    private String format;

    private Info info;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The format
     */
    public String getFormat() {
        return format;
    }

    /**
     *
     * @param format
     * The format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     *
     * @return
     * The info
     */
    public Info getInfo() {
        return info;
    }

    /**
     *
     * @param info
     * The info
     */
    public void setInfo(Info info) {
        this.info = info;
    }

    public class Info {
        private String title;

        private String name;

        private String format;

        private List<String> options = new ArrayList<String>();

        /**
         * @return The title
         */
        public String getTitle() {
            return title;
        }

        /**
         * @param title The title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return The name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name The name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return The format
         */
        public String getFormat() {
            return format;
        }

        /**
         * @param format The format
         */
        public void setFormat(String format) {
            this.format = format;
        }

        /**
         *
         * @return
         * The options
         */
        public List<String> getOptions() {
            return options;
        }

        /**
         *
         * @param options
         * The options
         */
        public void setOptions(List<String> options) {
            this.options = options;
        }
    }

}