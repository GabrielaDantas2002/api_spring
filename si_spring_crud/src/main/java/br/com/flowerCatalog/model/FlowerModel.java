package br.com.flowerCatalog.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Table(name = "flowers")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Flower Model")
public class FlowerModel extends RepresentationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter @Getter
    @ApiModelProperty(notes = "The ID, auto generated by database", required = true)
    private long id;

    @Column(name = "name", nullable = false, length = 50)
    @Setter @Getter
    @ApiModelProperty(notes = "The name", required = true)
    private String name;

    @Column(nullable = false, length = 50)
    @Setter @Getter
    @ApiModelProperty(notes = "The scientific name", required = true)
    private String scientificName;

    @Column(nullable = false, length = 1)
    @Setter @Getter
    @ApiModelProperty(notes = "The color", required = true)
    private String color;

    @ManyToOne
    @JoinColumn(name = "families_id")
    @Getter @Setter
    private FamilyModel family;

}
