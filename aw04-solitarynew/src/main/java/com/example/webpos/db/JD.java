//package com.example.webpos.db;
//
//import com.example.webpos.model.Cart;
//import com.example.webpos.model.Product;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Repository;
//
//import java.io.*;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
////@Repository
//public class JD implements PosDB {
//
//    static String cache_url = null;
//    private List<Product> products = null;
//
//    @Override
//    public List<Product> getProducts() {
//        if (products != null) return products;
//        try {
//            if (cache_url == null) cache_url = this.getClass().getClassLoader().getResource("cache.csv").getPath();
//            List<Product> cache = getCache();
//            if (cache != null) {
//                products = cache;
//                return products;
//            }
//        } catch (IOException ignored) {
//        }
//
//        try {
//            if (products == null)
//                products = parseJD("Java");
//        } catch (IOException e) {
//            products = new ArrayList<>();
//        }
//        return products;
//    }
//
//    public List<Product> getCache() throws IOException {
//        File file = new File(cache_url);
//        if (file.exists()) {
//            List<Product> products = new ArrayList<>();
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String line;
//            while ((line=br.readLine())!=null) {
//                String[] p = line.split(",");
//                if (p.length == 4) {
//                    products.add(new Product(p[0], p[1], Double.parseDouble(p[2]), p[3]));
//                }
//            }
//            br.close();
//            return products;
//        }
//        return null;
//    }
//
//    @Override
//    public Product getProduct(String productId) {
//        for (Product p : getProducts()) {
//            if (p.getId().equals(productId)) {
//                return p;
//            }
//        }
//        return null;
//    }
//
//    public static List<Product> parseJD(String keyword) throws IOException {
//        File file = new File(cache_url);
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
//        //获取请求https://search.jd.com/Search?keyword=java
//        String url = "https://search.jd.com/Search?keyword=" + keyword;
//        //解析网页
//        Document document = Jsoup.connect(url)
//                .timeout(10000)
//                //.proxy("127.0.0.1", 8456)
//                .get();
//        //所有js的方法都能用
//        Element element = document.getElementById("J_goodsList");
//        //获取所有li标签
//        Elements elements = element.getElementsByTag("li");
////        System.out.println(element.html());
//        List<Product> list = new ArrayList<>();
//
//        //获取元素的内容
//        for (Element el : elements
//        ) {
//            //关于图片特别多的网站，所有图片都是延迟加载的
//            String id = el.attr("data-spu");
//            String img = "https:".concat(el.getElementsByTag("img").eq(0).attr("data-lazy-img"));
//            String price = el.getElementsByAttribute("data-price").text();
//            String title = el.getElementsByClass("p-name").eq(0).text();
//            if (title.indexOf("，") >= 0)
//                title = title.substring(0, title.indexOf("，"));
//
//            Product product = new Product(id, title, Double.parseDouble(price), img);
//            bw.write(id + "," + title + "," + price + "," + img + "\n");
//            bw.flush();
//            list.add(product);
//        }
//        bw.close();
//        return list;
//    }
//
//
//
//}
