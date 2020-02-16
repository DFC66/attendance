package com.dfc.utils;

/**
 * @author: zsh
 * @Date:19:51 2018/5/6
 * @Description:
 */
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @jxy
 * 条件模糊查询拼接要查询哪些属性公共类
 * @param <T>
 */
public class SpecificationUtil<T> {
    private String search;
    public SpecificationUtil() {

    }
    public SpecificationUtil(String search) {
        this.search=search;
    }

    /**
     * 查询String类型的属性
     * @param strings
     * @return
     */
    public Specification<T> getSpe(String...strings){
        return  new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root,
                                         CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();

                //按其他属性查询
                for (String s:strings){
                    Path<String> $name = root.get(s);
                    Predicate _name = criteriaBuilder.like($name, "%" + search + "%");
                    predicates.add(_name);
                }
                return criteriaBuilder.or(predicates
                        .toArray(new Predicate[] {}));
            }
        };
    }


    /**
     * 查询int和String类型的属性
     * @param ints
     * @param strings
     * @return
     */
    public Specification<T> getSpe(String[] ints,String...strings){
        return  new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root,
                                         CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                //按int属性查询
                if (NumberUtils.isNumber(search) && search.length() < 10) {
                    for (String is:ints){
                        Path<Integer> $name = root.get(is);
                        Predicate _name = criteriaBuilder.equal($name, Integer.valueOf(search));
                        predicates.add(_name);
                    }
                }
                //String属性
                for (String s:strings){
                    Path<String> $name = root.get(s);
                    Predicate _name = criteriaBuilder.like($name, "%" + search + "%");
                    predicates.add(_name);
                }
                return criteriaBuilder.or(predicates
                        .toArray(new Predicate[] {}));
            }
        };
    }

    public Specification<T> getSpeByCondition(String[] intName, Integer[] ints, String[] strName, String[] strs, String...strings){
        return  new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root,
                                         CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> orPredicates = new ArrayList<Predicate>();
                //OR
                if (strings.length!=0){
                    for (String s:strings){
                        Path<String> $name = root.get(s);
                        Predicate _name = criteriaBuilder.like($name, "%" + search + "%");
                        orPredicates.add(_name);
                    }
                }

                //AND
                List<Predicate> andPredicates = new ArrayList<Predicate>();
                if (intName!=null && ints!=null&&ints.length==intName.length ) {
                    //int
                    for (int i = 0; i < ints.length; i++) {
                        Path<Integer> $name = root.get(intName[i]);
                        Predicate _name = criteriaBuilder.equal($name, ints[i]);
                        andPredicates.add(_name);
                    }
                }
                if ( strName.length==strs.length){
                    //String
                    for (int i=0;i<strs.length;i++){
                        Path<String> $name = root.get(strName[i]);
                        Predicate _name = criteriaBuilder.equal($name,strs[i]);
                        andPredicates.add(_name);
                    }
                }

                return criteriaBuilder.and(
                        criteriaBuilder.and(criteriaBuilder.and(andPredicates.toArray(new Predicate[] {}))),
                        criteriaBuilder.or(criteriaBuilder.or(orPredicates.toArray(new Predicate[] {}))));

            }
        };
    }

    public Specification<T> getSpeAllByCondition(String[] intName, Integer[] ints,String[] strName, String[] strs){
        return  new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root,
                                         CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                //AND
                List<Predicate> andPredicates = new ArrayList<Predicate>();
                if (intName!=null && ints!=null&&ints.length==intName.length ) {
                    //int
                    for (int i = 0; i < ints.length; i++) {
                        Path<Integer> $name = root.get(intName[i]);
                        Predicate _name = criteriaBuilder.equal($name, ints[i]);
                        andPredicates.add(_name);
                    }
                }
                if ( strName.length==strs.length){
                    //String
                    for (int i=0;i<strs.length;i++){
                        Path<String> $name = root.get(strName[i]);
                        Predicate _name = criteriaBuilder.equal($name,strs[i]);
                        andPredicates.add(_name);
                    }
                }

                return criteriaBuilder.and(criteriaBuilder.and(andPredicates.toArray(new Predicate[] {})));

            }
        };
    }

    public Specification<T> getSpe(String[] intName,Integer[] ints){
        return  new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root,
                                         CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                //String属性
                for (int i = 0; i < intName.length; i++){
                    Path<String> $name = root.get(intName[i]);
                    Predicate _name = criteriaBuilder.equal($name, ints[i]);
                    predicates.add(_name);
                }
                return criteriaBuilder.or(predicates
                        .toArray(new Predicate[] {}));
            }
        };
    }

    public Specification<T> getSpe(String[] intName,Integer[] value,String[] ints,String...strings){
        return  new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root,
                                         CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                for (int i = 0; i < intName.length; i++) {
                    Path<String> $schoolId = root.get(intName[i]);
                    //按int属性查询
                    if (NumberUtils.isNumber(search) && search.length() < 10) {
                        for (String is:ints){
                            Path<Integer> $name = root.get(is);
                            Predicate _name = criteriaBuilder.and(criteriaBuilder.equal($schoolId, value[i]),criteriaBuilder.equal($name, Integer.valueOf(search)));
                            predicates.add(_name);
                        }
                    }
                    //String属性
                    for (String s:strings){
                        Path<String> $name = root.get(s);
                        Predicate _name = criteriaBuilder.and(criteriaBuilder.equal($schoolId, value[i]),criteriaBuilder.like($name, "%" + search + "%"));
                        predicates.add(_name);
                    }
                }
                return criteriaBuilder.or(predicates
                        .toArray(new Predicate[] {}));
            }
        };
    }


}
