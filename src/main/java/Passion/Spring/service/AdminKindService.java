package Passion.Spring.service;

import Passion.Spring.controller.KindForm;
import Passion.Spring.domain.Kind;
import Passion.Spring.domain.Member;
import Passion.Spring.repository.KindRepository;
import java.util.List;
import java.util.Optional;

public class AdminKindService
{
    private final KindRepository kindRepository;

    public AdminKindService(KindRepository kindRepository) {
        this.kindRepository = kindRepository;
    }


    public List<Kind> findKinds()
    {
        return kindRepository.findAll();
    }
    public Optional<Kind> findByNo(Long no)
    {
        return kindRepository.findByNo(no);
    }
    public Kind editFormKindObject(Kind kind, KindForm kindForm)
    {
        kind.setNo(kindForm.getNo());
        kind.setName(kindForm.getName());
        return kind;
    }
    public void updateKind(Kind kind)
    {
        kindRepository.save(kind);
    }

    public void deleteByNo(Long no)
    {
        kindRepository.deleteByNo(no);
    }
}
