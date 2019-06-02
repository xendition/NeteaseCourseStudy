package com.michael.demos.elasticsearch.repository;

import com.michael.demos.elasticsearch.model.GoodsInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/25 16:29
 */
public interface GoodsRepository extends ElasticsearchRepository<GoodsInfo, String> {
}
