package com.fourdev.webapi.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fourdev.webapi.domain.IDomain;
import com.fourdev.webapi.infrastructure.api.MapperInterface;
import com.fourdev.webapi.infrastructure.api.ServiceInterface;
import com.fourdev.webapi.infrastructure.dto.IDto;

/**
 * @author stevenreis
 * @since 1.0 (09/05/24)
 */
public class AbstractEndpoint<T extends IDto, K extends IDomain> {

    @Autowired(required=false)
    protected ServiceInterface<K> abstractService;

    @Autowired(required=false)
    protected MapperInterface<K, T> abstractMapper;

    @GetMapping
    public ResponseEntity findAll() {

        List<T> data = this.abstractMapper.convertListToDto(this.abstractService.findAll());

        return new ResponseEntity(new DataResponse<>(data), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<T> findById(@PathVariable Long id) {

        K domain = this.abstractService.findById(id);

        return ok(abstractMapper.convertToDto(domain));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody T entity) {

        K data = this.abstractService.insert(this.abstractMapper.convertToDomain(entity));

        return new ResponseEntity<>(getDataWithMessage(abstractMapper.convertToDto(data),
                "sucesso"), HttpStatus.CREATED);
    }

    protected ResponseEntity ok(T data) {
        return new ResponseEntity<>(new DataResponse<>(data), HttpStatus.OK);
    }

    protected ResponseEntity ok(Map<String, Object> data) {
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    protected ResponseEntity ok() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected Map<String, Object> getDataWithMessage(T data, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", data);

        return response;
    }
}
