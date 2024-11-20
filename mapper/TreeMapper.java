package ma.hariti.asmaa.wrm.citrontrack.mapper;

import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeRequestDTO;
import ma.hariti.asmaa.wrm.citrontrack.dto.tree.TreeResponseDTO;
import ma.hariti.asmaa.wrm.citrontrack.entity.Tree;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface TreeMapper {

    @Mapping(source = "field.id", target = "field.id")
    TreeResponseDTO toResponseDto(Tree tree);

    @Mapping(source = "field.id", target = "fieldId")
    TreeDTO toDto(Tree tree);

    @Mapping(source = "fieldId", target = "field.id")
    Tree toEntity(TreeDTO treeDTO);

    @Mapping(source = "fieldId", target = "field.id")
    Tree toEntityFromRequest(TreeRequestDTO treeRequestDTO);

    void updateEntityFromDto(TreeDTO treeDTO, @MappingTarget Tree tree);

    void updateEntityFromRequest(TreeRequestDTO treeRequestDTO, @MappingTarget Tree tree);
}