package com.cantuaria.bookkeeping;

import com.cantuaria.bookkeeping.model.ConsolidateResponseBookkeeping;
import com.cantuaria.bookkeeping.model.DetailBookkeeping;
import com.cantuaria.bookkeeping.model.ItemResponseBookkeeping;
import com.cantuaria.bookkeeping.model.ResponseBookkeeping;
import com.cantuaria.bookkeeping.model.SaveBookkeeping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookkeeping")
@Tag(name = "Exemplo", description = "Endpoints de exemplo")
public class BookkeepingController {

    @Autowired
    private BookkeepingService service;

    @GetMapping
    @Operation(summary = "Listar escriturações", description = """
            Apresenta todas as escriturações na base de dados
            """)
    public List<ItemResponseBookkeeping> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhes básicos de uma escrituração", description = """
            Detalha por ID as informações basicas da escrituração
            """)
    public DetailBookkeeping detailBasicById(@PathVariable Long id) {
        return service.seachById(id);
    }

    @PostMapping
    @Operation(summary = "Criar uma nova escrituração", description = """
            Aqui apenas a solicitação de uma nova solicitação de escrituração é criada.
            Ou seja, a intensão do usuário em iniciar uma nova escrituração
            """)
    public ResponseEntity<ResponseBookkeeping> createBookkeeping(@RequestBody SaveBookkeeping bookkeeping){
        return ResponseEntity.ok(service.create(bookkeeping));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar uma escrituração", description = """
            A escrituração só pode ser alterada se ela estiver em desenvolvimento
            """)
    public ResponseBookkeeping updateBookkeeping(@PathVariable Long id, SaveBookkeeping bookkeeping){
        return service.update(id, bookkeeping);
    }

    @PostMapping("/consolidate/{id}")
    @Operation(summary = "Iniciar a consolidação da escrituração", description = """
            Processo assíncrono que dará início a consolidação. Isso pode demorar alguns minutos
            para completar.
            """)
    public ConsolidateResponseBookkeeping consolidate(@PathVariable Long id){
        return service.consolidate(id);
    }

    @PutMapping("/consolidate/{id}")
    @Operation(summary = "Recria uma consolidação em uma escrituração", description = """
            Processo assíncrono que irá atualizar os dados de uma escrituração
            que já está consolidada, mas precisa de ajustes.
            Isso pode demorar alguns minutos para completar.
            """)
    public ConsolidateResponseBookkeeping reConsolidate(@PathVariable Long id){
        return service.reConsolidate(id);
    }

    @GetMapping("/consolidate/{id}")
    @Operation(summary = "Recria uma consolidação em uma escrituração", description = """
            Processo assíncrono que irá atualizar os dados de uma escrituração
            que já está consolidada, mas precisa de ajustes.
            Isso pode demorar alguns minutos para completar.
            """)
    public ConsolidateResponseBookkeeping searchConsolidateById(@PathVariable Long id){
        return service.searchConsolidateById(id);
    }


}