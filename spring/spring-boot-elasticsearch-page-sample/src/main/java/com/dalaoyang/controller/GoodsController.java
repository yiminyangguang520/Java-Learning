package com.dalaoyang.controller;

import com.dalaoyang.entity.GoodsInfo;
import com.dalaoyang.repository.GoodsRepository;
import java.util.List;
import java.util.Optional;
import org.elasticsearch.common.lucene.search.function.FiltersFunctionScoreQuery.ScoreMode;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder.FilterFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dalaoyang
 * @Description
 * @project springboot_learn
 * @package com.dalaoyang.controller
 * @email yangyang@dalaoyang.cn
 * @date 2018/5/4
 */
@RestController
public class GoodsController {

  @Autowired
  private GoodsRepository goodsRepository;
  //每页数量
  private Integer PAGESIZE = 10;

  //http://localhost:8888/save
  @GetMapping("save")
  public String save() {
    GoodsInfo goodsInfo = new GoodsInfo(System.currentTimeMillis(),
        "商品" + System.currentTimeMillis(), "这是一个测试商品");
    goodsRepository.save(goodsInfo);
    return "success";
  }

  //http://localhost:8888/delete?id=1525415333329
  @GetMapping("delete")
  public String delete(long id) {
    goodsRepository.deleteById(id);
    return "success";
  }

  //http://localhost:8888/update?id=1525417362754&name=修改&description=修改
  @GetMapping("update")
  public String update(long id, String name, String description) {
    GoodsInfo goodsInfo = new GoodsInfo(id, name, description);
    goodsRepository.save(goodsInfo);
    return "success";
  }

  //http://localhost:8888/getOne?id=1525417362754
  @GetMapping("getOne")
  public GoodsInfo getOne(long id) {
    Optional<GoodsInfo> goodsInfo = goodsRepository.findById(id);
    return goodsInfo.get();
  }

  //http://localhost:8888/getGoodsList?query=商品
  //http://localhost:8888/getGoodsList?query=商品&pageNumber=1
  //根据关键字"商品"去查询列表，name或者description包含的都查询
  @GetMapping("getGoodsList")
  public List<GoodsInfo> getList(Integer pageNumber, String query) {
    if (pageNumber == null) {
      pageNumber = 0;
    }
    //es搜索默认第一页页码是0
    SearchQuery searchQuery = getEntitySearchQuery(pageNumber, PAGESIZE, query);
    Page<GoodsInfo> goodsPage = goodsRepository.search(searchQuery);
    return goodsPage.getContent();
  }


  private SearchQuery getEntitySearchQuery(int pageNumber, int pageSize, String searchContent) {
    FilterFunctionBuilder[] functions = {
        new FunctionScoreQueryBuilder.FilterFunctionBuilder(
            QueryBuilders.matchPhraseQuery("name", searchContent),
            ScoreFunctionBuilders.weightFactorFunction(100)),
        new FunctionScoreQueryBuilder.FilterFunctionBuilder(
            QueryBuilders.matchPhraseQuery("description", searchContent),
            ScoreFunctionBuilders.weightFactorFunction(100))
    };
    FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(functions)
        //设置权重分 求和模式
        .scoreMode(ScoreMode.SUM)
        //设置权重分最低分
        .setMinScore(10);

    // 设置分页
    Pageable pageable = new PageRequest(pageNumber, pageSize);
    return new NativeSearchQueryBuilder()
        .withPageable(pageable)
        .withQuery(functionScoreQueryBuilder).build();
  }

}
