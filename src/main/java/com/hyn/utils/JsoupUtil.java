package com.hyn.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * 描述: 过滤 HTML 标签中 XSS 代码
 * @author mumu
 */
public class JsoupUtil {
    /**
     * 使用自带的 basicWithImages 白名单
     * 允许的便签有 a,b,blockquote,br,cite,code,dd,dl,dt,em,i,li,ol,p,pre,q,small,span,strike,strong,sub,sup,u,ul,img
     * 以及 a 标签的 href,img 标签的 src,align,alt,height,width,title 属性
     */
    private final static Whitelist whitelist = Whitelist.basic();
    /**
     * 配置过滤化参数, 不对代码进行格式化
     */
    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);

    static {
        // 富文本编辑时一些样式是使用 style 来进行实现的
        // 比如红色字体 style="color:red;"
        // 所以需要给所有标签添加 style 属性
        whitelist.addAttributes(":all", "style");
        //table tab
        whitelist.addTags("table");
        whitelist.addAttributes("table", "class", "id", "style");
        //img
        whitelist.addTags("img");
        whitelist.addAttributes("img", "align", "alt", "height", "src", "title", "width", "class");
        //tbody
        whitelist.addTags("tbody");
        whitelist.addAttributes("tbody", "style");
        //tr
        whitelist.addTags("tr");
        whitelist.addAttributes("tr", "style");
        //td
        whitelist.addTags("td");
        whitelist.addAttributes("td", "style");
        //span
        whitelist.addTags("span");
        whitelist.addAttributes("span", "style");
        //p
        whitelist.addTags("p");
        whitelist.addAttributes("p", "style");
        //pre
        whitelist.addTags("pre");
        whitelist.addAttributes("pre", "style", "class");
        //code
        whitelist.addTags("code");
        whitelist.addAttributes("code", "style");
        //ul
        whitelist.addTags("ul");
        whitelist.addAttributes("ul", "style");
        //ol
        whitelist.addTags("ol");
        whitelist.addAttributes("ol", "style");
        //li
        whitelist.addTags("li");
        whitelist.addAttributes("li", "style");
        //dl
        whitelist.addTags("dl");
        whitelist.addAttributes("dl", "style");
        //dt
        whitelist.addTags("dt");
        whitelist.addAttributes("dt", "style");
        //dd
        whitelist.addTags("dd");
        whitelist.addAttributes("dd", "style");
        //thead
        whitelist.addTags("thead");
        whitelist.addAttributes("thead", "style");
        //b
        whitelist.addTags("b");
        whitelist.addAttributes("b", "style");
        //i
        whitelist.addTags("i");
        whitelist.addAttributes("i", "style");
        //em
        whitelist.addTags("em");
        whitelist.addAttributes("em", "style");
        //strong
        whitelist.addTags("strong");
        whitelist.addAttributes("strong", "style");
        //br
        whitelist.addTags("br");

    }

    public static String clean(String content) {
        return Jsoup.clean(content, "", whitelist, outputSettings);
    }

}