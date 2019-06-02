package com.michael.demos.elasticsearch.web;

import com.michael.demos.elasticsearch.model.GoodsInfo;
import com.michael.demos.elasticsearch.repository.GoodsRepository;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.facet.FacetRequest;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * 类功能描述:
 *
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/25 16:30
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;

    //每页数量
    private Integer PAGESIZE = 10;

    @GetMapping("save")
    public String save() {
        GoodsInfo goodsInfo =
                new GoodsInfo(UUID.randomUUID().toString(), "商品" + System.currentTimeMillis(), "这是一个测试商品");
        this.goodsRepository.save(goodsInfo);
        return "success";
    }

    @GetMapping("delete")
    public String delete(String id) {
        this.goodsRepository.deleteById(id);
        return "success";
    }

    @GetMapping("update")
    public String update(String id, String name, String description) {

        GoodsInfo goodsInfo = new GoodsInfo(id, name, description);
        this.goodsRepository.save(goodsInfo);

        return "success";
    }

    @GetMapping("list")
    public String list() {

        // this.goodsRepository.findAll()

        Iterable<GoodsInfo> iterable = this.goodsRepository.findAll();

        String result = "";
        for (GoodsInfo goodsInfo : iterable) {

            result = goodsInfo.toString();
        }

        return result;
    }


    // @GetMapping("getGoodsList")
    // public List<GoodsInfo> getList(Integer pageNumber, String query) {
    //     if (pageNumber == null) {
    //         pageNumber = 0;
    //     }
    //     //es搜索默认第一页页码是0
    //     SearchQuery searchQuery = this.getEntitySearchQuery(pageNumber, this.PAGESIZE, query);
    //     Page<GoodsInfo> goodsPage = this.goodsRepository.search(searchQuery);
    //     return goodsPage.getContent();
    // }

    // private SearchQuery getEntitySearchQuery(int pageNumber, int pageSize, String searchContent) {
    //     FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
    //                                                                        .add(
    //                                                                                QueryBuilders
    //                                                                                        .matchPhraseQuery(
    //                                                                                                "name",
    //                                                                                                searchContent
    //                                                                                        ),
    //                                                                                ScoreFunctionBuilders
    //                                                                                        .weightFactorFunction(100)
    //                                                                        )
    //                                                                        .add(
    //                                                                                QueryBuilders.matchPhraseQuery(
    //                                                                                        "description",
    //                                                                                        searchContent
    //                                                                                ),
    //                                                                                ScoreFunctionBuilders
    //                                                                                        .weightFactorFunction(100)
    //                                                                        )
    //                                                                        //设置权重分 求和模式
    //                                                                        .scoreMode("sum")
    //                                                                        //设置权重分最低分
    //                                                                        .setMinScore(10);
    //
    //     // 设置分页
    //     Pageable pageable = new PageRequest(pageNumber, pageSize);
    //     return new NativeSearchQueryBuilder()
    //             .withPageable(pageable)
    //             .withQuery(functionScoreQueryBuilder).build();
    // }

}
