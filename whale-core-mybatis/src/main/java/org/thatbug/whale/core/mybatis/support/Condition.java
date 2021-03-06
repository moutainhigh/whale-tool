package org.thatbug.whale.core.mybatis.support;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.thatbug.whale.core.mybatis.annotations.IsLike;
import org.thatbug.whale.core.tool.utils.BeanUtil;
import org.thatbug.whale.core.tool.utils.Func;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 分页工具
 *
 * @author qzl
 * @date 14:51 2019/9/19
 */
public class Condition {

    /**
     * 转化成mybatis plus中的Page
     *
     * @param query
     * @return
     */
    public static <T> IPage<T> getPage(Query query) {
        Page<T> page = new Page<>(Func.toInt(query.getCurrent(), 1), Func.toInt(query.getSize(), 10));
        page.setAsc(Func.toStrArray(SqlKeyword.filter(query.getAscs())));
        page.setDesc(Func.toStrArray(SqlKeyword.filter(query.getDescs())));
        return page;
    }

    /**
     * 获取mybatis plus中的QueryWrapper
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> QueryWrapper<T> getQueryWrapper(T entity) {
        QueryWrapper<T> tQueryWrapper = new QueryWrapper<>(entity);
        Class<?> aClass = entity.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            IsLike isLike = field.getAnnotation(IsLike.class);
            if (isLike == null) {
                continue;
            }
            try {
                //打开私有访问
                field.setAccessible(true);
                Object o = field.get(entity);
                if (o != null) {
                    TableField tableField = field.getAnnotation(TableField.class);
                    String name;
                    if (tableField !=null) {
                        name = tableField.value();
                    }else {
                        name = field.getName();
                    }
                    field.set(entity,null);
                    tQueryWrapper.like(name,o);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }

        return tQueryWrapper;
    }

    /**
     * 获取mybatis plus中的QueryWrapper
     *
     * @param query
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> QueryWrapper<T> getQueryWrapper(Map<String, Object> query, Class<T> clazz) {
        query.remove("current");
        query.remove("size");
        query.remove("ascs");
        query.remove("descs");
        QueryWrapper<T> qw = new QueryWrapper<>();
        qw.setEntity(BeanUtil.newInstance(clazz));
        SqlKeyword.buildCondition(query, qw);
        return qw;
    }

}
