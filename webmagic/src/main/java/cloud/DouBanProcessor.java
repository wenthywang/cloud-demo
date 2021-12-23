package cloud;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class DouBanProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(5000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.2; Win64; x64; rv:16.0.1) Gecko/20121011 Firefox/16.0.1");

    private int count = 0;

    public void process(Page page) {
        Selectable selectable = page.getHtml().xpath("//table[@class='olt']//tr");
        List<Selectable> lists = selectable.nodes();
        List<DoubanInfo> infos = new ArrayList<>();
        for (Selectable tet : lists) {
            DoubanInfo info = new DoubanInfo();
            info.setTitle(tet.xpath("//a/text()").toString());
            info.setUrl(tet.xpath("//a").links().toString());
            info.setDate(tet.xpath("//td[@class=\"time\"]/text()").toString());
            infos.add(info);

        }
        page.putField("infos", infos);
        count += 25;
        String newUrl = "https://www.douban.com/group/637323/discussion?start=" + count;
        if (count <= 3000) {
            page.addTargetRequest(newUrl);
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);
            executor.scheduleAtFixedRate(()->{
                //删除表中所有数据
                String sql = "delete from douban_info";
                JdbcUtil.executeSQL(sql);
                String sql2 = "delete from douban_target";
                JdbcUtil.executeSQL(sql2);
                //再执行 爬取
                Spider.create(new DouBanProcessor())
                        .addUrl("https://www.douban.com/group/637323/discussion?start=0")
                        .addUrl("https://www.douban.com/group/tianhezufang/discussion?start=0")
                        .thread(5)
                        .addPipeline(new mysqlPipeline())
                        .run();
            } , 0 ,10, TimeUnit.MINUTES);
    }
}