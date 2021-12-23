package cloud;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

public class mysqlPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {

        List<DoubanInfo> infos = resultItems.get("infos");

        for (DoubanInfo info:infos) {
            if(info.getTitle()==null){
                continue;
            }
            String id=info.getUrl().split("/topic/")[1].replaceAll("/","");

            String sql = "INSERT INTO douban_info "
                    + "(id,title, url, date ) VALUES ( '" + //
                   id.replace("'", "\\\'") + "', '" + //
                    info.getTitle().replace("'", "\\\'") + "', '" + //
                    info.getUrl().replace("'", "\\\'") + "', '" + //
                    info.getDate().replace("'", "\\\'") + "' );";
            JdbcUtil.executeSQL(sql);
            if(info.getTitle().contains("主卧")||info.getTitle().contains("次卧")||info.getTitle().contains("合租")
                    ||info.getTitle().contains("已租")||info.getTitle().contains("已转")||info.getTitle().contains("求租")){
                continue;
            }
            if(info.getTitle().contains("单间")&&!info.getTitle().contains("一房一厅")){
                continue;
            }

            if(info.getTitle().contains("车陂")&&info.getTitle().contains("一房一厅")||info.getTitle().contains("车陂"))
            {
                String sql2 = "INSERT INTO douban_target "
                        + "(id,title, url, date ) VALUES ( '" + //
                        id.replace("'", "\\\'") + "', '" + //
                        info.getTitle().replace("'", "\\\'") + "', '" + //
                        info.getUrl().replace("'", "\\\'") + "', '" + //
                        info.getDate().replace("'", "\\\'") + "' );";
                JdbcUtil.executeSQL(sql2);
            }


        }


    }
}
