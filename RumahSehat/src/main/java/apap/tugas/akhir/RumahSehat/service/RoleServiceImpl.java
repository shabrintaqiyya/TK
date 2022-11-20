// package apap.tugas.akhir.RumahSehat.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import apap.tugas.akhir.RumahSehat.model.RoleModel;
// import apap.tugas.akhir.RumahSehat.repository.RoleDb;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class RoleServiceImpl implements RoleService {
//     @Autowired
//     private RoleDb roleDb;

//     @Override
//     public RoleModel addRole(RoleModel role){
//         return roleDb.save(role);
//     }

//     @Override
//     public List<RoleModel> findAll(){
//         return roleDb.findAll();
//     }

//     @Override
//     public RoleModel getById(Long id){
//         Optional<RoleModel> role = roleDb.findById(id);
//         if (role.isPresent()) {
//             return role.get();

//         } else
//             return null;
//     }

// }